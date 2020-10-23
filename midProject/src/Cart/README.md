##### - Cart

두번째 액티비티에서는 **장바구니**에 관한 처리를 할 수 있도록 하였다.
장바구니에 담긴 상품을 확인하거나, 장바구니에서 상품을 선택하여 구매할 수 있다.

###### *activity_cart.xml (부분생략)*

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
        .
        .
        .
    </ScrollView>
    .
    .
    .
</LinearLayout>
```
전체적인 레이아웃은 `LinearLayout`으로 이루어져 있으며, 그 안에 `ScrollView`를 추가하여 화면이 스크롤 될 수 있도록 구현하였다.

```xml
  <LinearLayout
      android:id="@+id/add_item"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:gravity="top"
      android:orientation="vertical">

      <View
          android:layout_width="match_parent"
          android:layout_height="0.5dp"
          android:background="#d3d3d3"/>
  </LinearLayout>
```
그리고 동적인 뷰를 생성하기 위해 `@+id/add_item` 을 가진 리니어 레이아웃을 추가하고, java파일에서 프로그래밍을 통하여 뷰를 추가하였다.

###### *cartActivity.class (부분생략)*

앞서 `MainActivity.class`에서와 같이 레이아웃에 해당하는 `ID`를 통해 레이아웃을 가져오고 설정하는 부분은 동일하다.

`cartActivity.class`에서는 먼저 `MainActivity.class`에서 받은 `Cart`타입의 객체와 그에 해당하는 `Index`를 담는 `ArrayList`를 받아온 후에 이를 이용하여 아이템에 대한 정보를 가져와 화면에 출력하도록 한다.

하지만 선택된 아이템의 수를 정확하게 파악할 수 없기 때문에 동적인 뷰를 생성하는 방향으로 선택하였다.

```java
for (int num:cartNum){
  //상품이 String[] = {Type, Title, Option, Price, Cnt}로 이루어져있음
  String[] obj = item.getItems(num);
  cartLay = new LinearLayout(this);
  //동적으로 추가하기 위한 레이아웃과 그에 대한 Parameter설정
  LinearLayout.LayoutParams cartParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

```
`Index`로 접근하기 위해 `ArrayList`를 탐색하였고, 그 `Index`에 해당하는 아이템을 가져오도록 한다.

또한 `cartLay`라는 레이아웃을 임시로 `LinearLayout`으로 새로 생성해 줌으로써 `cartLay`에 뷰들을 add하고 이 `cartLay`를 xml파일에 있는 `@+id/add_item`에 add해 준다.

이렇게 동적인 뷰를 추가하게 되면 선택한 상품을 수에 상관없이 추가할 수 있다.

```java
  itemCk = new CheckBox(this);
  itemCk.setWidth(100);
  itemCk.setId(num);
```
동적인 뷰를 생성하였기 때문에, `id`를 int형으로만 줘야했기 때문에 처리하기에는 체크박스만 `id`를 할당해 주는 것이 낫다는 판단을 했다.
따라서 체크박스에 아이템에 해당하는 `Index`를 줌으로써, 번호표 같은 역할을 하도록 하였다.

이로써 체크박스가 체크된 경우 해당하는 `Id`를 가져오고 그 `Id`에 해당하는 `Index`로 처리할 수 있다.

하지만 현재로써 문제점이 하나 있는데,
* `Cart`객체에 있는 `ArrayList(String[])` 에 add되는 원소가 `String[]` 이므로, main에 있는 인덱스 List에서 만약 원소가 제거되어도, `ArrayList(String[])`에서는 제거되지 않는다. ~> *메모리 낭비가 될 수 있다.*

```java
//Item Img, Title, Option, Price, Cnt 생성
  itemImg = new ImageView(this);
  itemImg.setLayoutParams(cartParam);
  itemTitle = new TextView(this);
  itemTitle.setLayoutParams(cartParam);
  itemTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
  itemOption = new TextView(this);
  itemOption.setLayoutParams(cartParam);
  itemPrice = new TextView(this);
  itemPrice.setLayoutParams(cartParam);
  itemPrice.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
  itemCnt = new TextView(this);
  itemCnt.setLayoutParams(cartParam);
  itemCnt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
```
추가할 뷰들을 생성하고, Parameter들을 설정한다.

```java
//레이아웃에 추가
  cartLay.addView(itemCk);
  cartLay.addView(itemImg);
  cartLay.addView(itemTitle);
  cartLay.addView(itemOption);
  cartLay.addView(itemPrice);
  cartLay.addView(itemCnt);
  cartLay.setLayoutParams(cartParam);
  lay.addView(cartLay);
```
추가한 뷰들에 대해 출력할 텍스트나, 체크박스들을 초기화 해준 후에, 레이아웃에 추가한다.
그 이후에 `돌아가기, 구매하기` 버튼에 대한 이벤트를 처리하였는데, `MainActivity.class`에서 처리한 것과 구현하였다.
```java
  //MainActivity에 Item, CartIdx의 값을 반환 (go to Main)
  Intent backData = new Intent();
  backData.putExtra("backItem", item);
  backData.putExtra("backNum", finalCartNum);
  setResult(RESULT_OK, backData);
  finish();
```
`돌아가기` 버튼을 클릭하였을 때에, `Cart`객체와 `Index`를 포함하는 리스트를 반환하고, `setResult(RESULT_OK, 반환데이터)`를 통해 해당 액티비티에서 데이터를 반환하고 `finish()`를 통해 액티비티를 종료하였다.

```java
  //go to Buy
  Intent buyData = new Intent(getApplicationContext(), buyActivity.class);
  buyData.putExtra("buyItem", item);
  buyData.putExtra("buyNum", buyNum);
  startActivityForResult(buyData, 1);
```
`구매하기` 버튼을 클릭하였을 때에, `Index`를 통해 탐색하면서, 체크박스의 여부를 확인하고, 체크박수가 체크된 수를 확인하였을 때 0보다 크면 (선택된 상품이 있으면) 세번째 액티비티로 전환하게 된다.

```java
if (requestCode == 1){
   if (resultCode == RESULT_OK){
       for(int num:buyNum){
           cartNum.remove(cartNum.indexOf(num));
       }
       buyNum = new ArrayList<Integer>();
       gotoBack.performClick();
   }
}
```
마지막으로, 세번째 액티비티에서 `setResult`가 이루어졌을 경우 호출되는 함수로, 상품이 구매된 것들은 화면에서 제거하기 위해 호출하였다.
화면에서 제거가 된 후에, `buyNum`은 초기화 시키고, `돌아가기`버튼을 강제로 실행하게 하였다.