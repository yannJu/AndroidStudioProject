##### - MAIN

메인에서는 상품을 선택하여 장바구니에 담거나, 구매할 수 있도록 한다.

 ###### *activity_main.xml (부분생략)*

 ```xml

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
  .
  .
  .
   <RelativeLayout
          android:id="@+id/Item_Band"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_weight="1"
          android:layout_below="@+id/Title">
  .
  .
  .
  </RelativeLayout>
</RelativeLayout>
 ```
 먼저 `RelativeLayout`을 전체적인 Layout으로 설정을 한후, subLayout을 입력하였다.
`RelativeLayout`은 상대적인 위치로 표현하기 때문에, `@+id/Item)Band`또한 `layout_below`로 표현되어있는 모습을 확인 할 수 있다.

###### *MainActivity.class (부분생략)*

```java
//(1)TextView객체의 변수들 _ 상품명, 수량, 컬러, 가격
    //Band
    bandTxt = (TextView) findViewById(R.id.bandTitle);
    selectedText = (TextView) findViewById(R.id.colorTxt);
    setCnt = (TextView) findViewById(R.id.cntTxt);
    bandPrice = (TextView) findViewById(R.id.bandPrice);
```
먼저 레이아웃(*activity_main.xml*)에 출력하기 위해 `id`를 이용하여 뷰를 가져온다.
위와 같은 식으로 `Band, CirclePin, SquarePin`에도 똑같이 구현해 주었다.

```java
//(2)ButtonEvent
    //BandItem Plus, Minus
    cntPBt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cntInt += 1;
            setCnt.setText(String.valueOf(cntInt));
        }
    });
    cntMBt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (cntInt > 1) {cntInt -= 1;}
            setCnt.setText(String.valueOf(cntInt));
        }
    });
```
그 다음, 버튼에 관련한 이벤트를 처리해 주었다. `PBt, MBt`을 이용하여 수량을 추가하거나 감소시키도록 하였으며, 버튼외에도 `Spinner`를 이용하여 옵션(색상)을 선택할 수 있게 하였다.

```java
//Band Color Spinner Event
  spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
          selectedText.setText("" + parent.getItemAtPosition(position));
      }
      @Override
      public void onNothingSelected(AdapterView<?> parent){
          selectedText.setText("색상선택");
      }
  });
```
색상이 선택되지 않은 경우에는 default값으로 **색상선택** 으로 지정하였고, 그 외에 선택 된 경우는 선택한 텍스트가 출력되도록 구현하였다.
이 또한 위와 같은 식으로 모든 상품에 대해 구현해 주었다.

```java
//go to basket
  cartNum.add(allCnt++);
  c.addCart(1, (String) bandTxt.getText(), (String) selectedText.getText(), Integer.parseInt((String)bandPrice.getText()), cntInt);      
```
레이아웃과 버튼, 스피너에 대한 이벤트를 처리해 준 후에, 두번째 액티비티인 `cartActivity`로 전환하기 위한 부분을 처리해 주었다.
장바구니에 추가하는 버튼을 클릭하게 되면 앞서 `Cart`객체에 대해 언급한 것처럼,
`ArrayList<Integer> cartNum`에 해당하는 `Index`를 `add`해 준다.
그 이후에 `Cart c`객체에 새로운 아이템을 생성하도록 한다.

이러한 변수 `cartNum, c` 를 두번째 액티비티에 `putExtra`해준다.
그리고 `cartNum, c`를 주고받기 위해 `startActivityForResult`를 이용하여 데이터를 주고 받을 수 있도록 한다.

```java
//go to buy
  buyActivity.putExtra("bandTitle", bandTxt.getText());
  buyActivity.putExtra("ckCartBand", true);
  buyActivity.putExtra("bandCnt", cntInt);
  buyActivity.putExtra("bandColor", selectedText.getText());
  buyActivity.putExtra("bandPrice", Integer.parseInt((String) bandPrice.getText()));
```
세번째 액티비티인 `buyActivity`로 전환하기 위한 부분이다.
상품을 구매하기 버튼을 누르게 되면 장바구니와 연관이 없기 때문에, 일괄적으로 데이터를 하나하나 `putExtra`해 주었다.
`Band`외에도 위와 같이 `putExtra` 하였고, 장바구니와 다르게 데이터를 전달만 할 목적이기 때문에 `startActivity`를 이용하였다.

```java
//on activity result
  if (requestCode == 0) {
      if (resultCode == RESULT_OK) {
          c = (Cart) data.getSerializableExtra("backItem");
          cartNum = data.getExtras().getIntegerArrayList("backNum");
          allCnt = c.getSize();
      }
  }
```
마지막으로 장바구니에서 `돌아가기` 버튼을 눌렀을 경우, 장바구니에 변화가 있을 수 있기 때문에 `cartNum, c`를 다시 초기화 하고, `Index`에 해당하는 `allCnt`는 장바구니의 크기만큼 할당해 주었다.
