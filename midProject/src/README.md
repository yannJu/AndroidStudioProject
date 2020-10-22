## 2. 코드구현

 전체적인 소스파일에는 `AndroidManifest.xml`를

 메인화면을 이루는 `MainActivity.class`와 `activity_main.xml`,
 장바구니 화면을 이루는 두번째 activity인 `cartActivity.class`와 `activity_cart.xml`
 상품구매 화면을 이루는 세번째 activity인 `buyActivity.class`와 `activity_buy.xml`이 있다.

 또한, 상품들을 담는 객체인 `Cart`라는 타입의 객체를 만든 `Cart.java`파일과 Spinner에 보여질 상품을 담은 `itemColors.xml`파일이 포함되어있다.

 ##### - AndroidManifest.xml

 ###### *(부분생략)*

    <activity android:name=".MainActivity">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />

            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
    <activity android:name=".cartActivity"></activity>
    <activity android:name=".buyActivity"></activity>

 AndroidManifest.xml파일에 3개의 액티비티가 생성되어있음을 확인할 수 있다.

 ##### - Cart.class

 현재 이 코드에서는 `Cart`라는 객체를 이용하여 Intent를 전달하여 액티비티간의 데이터를 주고받고 있다.
 또한 `Serialize`를 이용하여 생성한 객체를 주고받는다.

 ###### *#cart.class*

```java
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<String[]> items;
    private int size = 0;

    public Cart(){
      //상품을 ArrayList<String[]>에 각각 담기 위해 변수를 선언
        items = new ArrayList<String[]>();
    }
    //생성자가 실행되면 item을 String[]타입으로 추가한다.
    public void addCart(int type, String title, String option, int price, int cnt){
        items.add(new String[]{String.valueOf(type), title, option, String.valueOf(price), String.valueOf(cnt)});
        size += 1;
    }

    public int getSize(){
        return size;
    }

    //idx를 입력받으면 해당 idx의 아이템을(String[]타입의) 반환
    public String[] getItems(int num){
        return items.get(num);
    }
}
```

`Cart`는 생성될때, 해당 아이템의 `Type(band, circlePin, squarePin), 상품명, 옵션(컬러), 가격, 수량`을 인자로 받은 후 String[]타입의 객체에 담아서 ArrayList에 add한다.

프로그램에서 `Cart`객체는 먼저 MainActivity에서 생성된다.
프로그램이 실행되는 동안 동일한 `Cart`객체만이 이동되어야한다.
(Cart객체가 계속 생성된다면, 상품을 유지할 수 없다.)

선택된 아이템들은 아이템의 정보를 담아 `Cart`객체에 담긴다.
그리고 그 객체가 담긴 `Index`를 `ArrayList<Integer>` 에 담는다.
따라서 Item의 정보가 담긴 `Cart`객체와 그 정보의 `Index`를 담는 `ArrayList<Integer>`가 액티비티 사이에서 전달되는 것이다.

`ArrayList<Integer>`에 담긴 `Index`를 이용하여 `Cart.getItems`를 호출하고 원하는 Item을 탐색할 수 있게 된다.

##### - Values

Spinner 리스트를 구현하기 위해 해당 item을 담는 리스트인 `xml`파일을 구현하였다.

###### *itemColors.xml*

```xml
<string-array name="bandColor">
        <item>색상선택</item>
        <item>그린</item>
        <item>라벤더</item>
        <item>인디핑크</item>
        <item>애쉬베이지</item>
        <item>크림베이지</item>
    </string-array>
```
위와 같이 item으로 묶어서 `string-array`로 구현하였고, 위처럼 설정하게 되면 스피너 리스트에 추가된다.