
##### - Buy

마지막 세번째 액티비티에서는 **상품구매**에 관한 처리가 이루어진다.
첫번째 액티비티에서 넘어오게 된 경우와, 두번째 액티비티에서 넘어오게 된 경우를 구분한다.

또한, 두번째 액티비티와 동일하게 동적인 뷰를 생성하게 된다.

###### *activity_buy.xml (부분생략)*

```xml
<GridLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_height="match_parent"
    android:layout_width="match_parent">
    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
```

세번째 액티비티는 `GridLayout`으로 이루어져 있으며, 두번째 액티비티와 동일하게 스크롤을 구현하기 위하여 `ScrollView`를 추가하였다.

```xml
//Item add
  <GridLayout
      android:id="@+id/add_cart"
      android:layout_columnWeight="1"
      android:layout_gravity="fill_horizontal"
      android:gravity="center"
      android:layout_width="wrap_content"
      android:layout_height="match_parent"
      android:orientation="horizontal"
      android:columnCount="1">
  </GridLayout>
```
동적인 뷰를 추가하는 `@+id/add_cart`부분이며, 이 부분에는 장바구니 혹은 메인에서 추가한 상품이 출력되는 부분이다.

동적인 뷰를 추가하는 부분은 이 레이아웃 다음에 하나 더 추가되는데, 그것은 배송정보, 즉 개인정보를 입력하는 부분이다.

###### *buyActivity.class (부분생략)*

세번째 액티비티는 이전 두개의 액티비티에 비해, 버튼이나 스피너, 체크박스등이 존재하지 않아서 상태가 변화하는 이벤트가 적다.

따라서 대부분이 레이아웃에 동적으로 뷰를 추가하는 부분이고 이러한 부분은 이전 액티비티에서 보았던 것과 비슷하게 구현이 되어있다.

먼저 이전 액티비티에서 값을 (`Cart, ArrayList, CkBox등`) 받아온 것을 변수에 할당하고, 레이아웃에 해당하는 `Id`를 설정한다.

```java
  if (ckMain == false) {
       buyNum = getIntent.getExtras().getIntegerArrayList("buyNum");
   }
 ```

그 다음 첫번째 액티비티에서 전환된 것인지 두번째 액티비티에서 전환된 것인지에 따라 상품의 `Index`가 담긴 리스트인 `buyNum`이 달라지게 된다.

첫번째 액티비티에서 전환되었을 경우 `Index`처리를 하지 않고 개별적으로 `putExtra()` 되었기 때문에, `ckMain`이 `true`인 경우에만 `buyNum`에 할당해주고, 그렇지 않은 경우에는 `new ArrayList`로 초기화 되어있게 된다.

이전의 액티비티들과 동일하게, 해당상품에 대한 정보를 동적인 뷰에 추가하여 화면에 add 함으로써 화면을 구현하였다.

동적인 뷰들을 다 추가하여 레이아웃이 완성된 후에는 버튼이벤트를 처리하였다.

```java
buyBt.setOnClickListener(new View.OnClickListener() {
  @Override
  public void onClick(View view) {
      if (name.getText().toString().equals("") || mainAdr.getText().toString().equals("") || phoneNum.getText().toString().equals("") || emailAdr.getText().toString().equals("") || mes.getText().toString().equals("")){
          AlertDialog.Builder noInfo = new AlertDialog.Builder(buyActivity.this);
          noInfo.setMessage("정확한 정보를 입력해주세요!");
          noInfo.setPositiveButton("확인", null);
          noInfo.create().show();
      }
      else {
          Intent getIntent = getIntent();
          setResult(RESULT_OK);
          Toast.makeText(getApplicationContext(), "구매가 완료되었습니다", Toast.LENGTH_LONG).show();
          finish();
      }
  }
});
```
`name, mainAdr, phoneNum, emailAdr, mes`는 개인정보(수취인명, 주소, 전화번호, 이메일주소, 배송메시지) 등을 입력받는 `EditText View`이고 이 뷰가 한칸이라도 비어있으면 경고창을 띄우게 하였다.

그렇지 않은 경우, 구매가 완료되었다는 `Toas message`를 띄우고 액티비티를 종료하게 된다.

###### 이전 액티비티 
[- 첫번째 액티비티(Main)](https://github.com/yannJu/AndroidStudioProject/tree/master/midProject/src/Main)
[- 두번째 액티비티(Cart)](https://github.com/yannJu/AndroidStudioProject/tree/master/midProject/src/Cart)