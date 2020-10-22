# Mobile Programming Mid Project

---

**목차**

- 프로젝트 설명
   - 목표

- 코드 구현
  - 전체 코드 요약
  - AndroidManifest.xml
  - Cart.class
  - Main
    - activity_main.xml
    - MainActivity.class
  - Cart
    - activity_cart.xml
    - cartActivity.class
  - Buy
    - activity_buy.xml
    - buyActivity.class
  - Values
    - itemColors.xml

- 코드 실행
    - 간단한 기능
    - 장바구니에 담기
    - 구매하기
      - 메인에서 바로구매
      - 장바구니에서 구매
    - 경고창 확인

---

## 1. 프로젝트 설명

해당 프로젝트는 **'상품선택', '장바구니', '상품구매'** 로 이루어진 세가지 액티비티를 구현하고 각각 주어진 기능을 수행하는 프로젝트이다.

첫번째 액티비티인 **'상품선택'** 액티비티(MainActivity)에선 두개 이상의 상품과 그에 해당하는 정보 (상품명, 가격 등)를 출력하고 선택한 상품을 장바구니에 담거나 구매하도록 한다.

두번째 액티비티인 **'장바구니'** 액티비티(cartActivity)에선 장바구니에 담은 상품과 상품의 정보를 출력하고, 상품을 선택하여 구매하거나 메인 화면으로 돌아갈 수 있도록 한다.

세번째 액티비티인 **'상품구매'** 액티비티(buyActivity)에선 상품명과 해당하는 상품의 정보를 출력하고, 상품의 총 구매금액을 출력. 마지막으로 수취인, 주소, 휴대전화 등 개인정보를 입력받은 후 구매할 수 있도록 한다.

#### - 목표

프로젝트에서 요구하는 부분 외에도 세세한 부분까지 구현 할 수 있도록 한다.
 * 메인화면에서 상품 선택
 * 상품을 선택 한 후에도 장바구니에 선택 하지 않은 상품은 유지
 * 메인화면을 벗어나 다른 액티비티로 전환될 경우 메인화면을 초기화

## 2. 코드 구현

- [Cart.class](https://github.com/yannJu/AndroidStudioProject/blob/master/midProject/src/Cart.java)
- [Main](https://github.com/yannJu/AndroidStudioProject/tree/master/midProject/src/Main)
- [Cart](https://github.com/yannJu/AndroidStudioProject/tree/master/midProject/src/Cart)
- [Buy](https://github.com/yannJu/AndroidStudioProject/tree/master/midProject/src/Buy)
- [Values]()

## 3. 코드 실행

###### 간단한 기능

- 메인화면에서 수량 조정하기
- 메인화면에서 옵션 설정하기
- 전체선택하기

1. 메인화면에서 수량 조정

![Pbt1](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/Pbt1.PNG)
![Pb2](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/Pbt2.PNG)
![Mb1](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/Mbt1.PNG)

수량 옆에 있는 `+, -` 버튼을 통해 수량을 설정할 수 있다.
만약 수량이 1개라면 더이상 수량을 줄일 수 없다.

2. 메인화면에서 옵션 설정하기

![spinner1](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/spinner1.PNG)
![spinner2](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/spinner2.PNG)

선택하기 위해 아래를 가리키는 화살표 모양을 클릭하게 되면 옵션 리스트가 나타나게 된다.

원하는 옵션을 선택하면 옵션이 설정되어 화면에 출력된다.

3. 전체선택하기

![ckBox1](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/ckbox1.PNG)
![cart1selecttoBuy](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/cart1selecttoBuy.PNG)

체크박스를 선택하면 위와 같이 원하는 상품을 개별 선택할 수 있다.

![allselect](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/allselect.PNG)
![allselectCart to Buy](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/allselectCart%20to%20Buy.PNG)

하지만 전체선택 버튼 혹은 체크박스를 클릭하게 되면 하나하나 클릭하지 않고, 전체상품을 선택할 수 있다.

###### 장바구니에 담기

아무것도 장바구니에 담지않으면 다음과 같이 화면에 출력된다

![emptyCart](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/emptyCart.PNG)

하지만 단일 혹은 여러개의 상품을 장바구니에 담게 되면 다음과 같이 출력된다.

![cartMany1](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/cartMany1.PNG)

###### 구매하기

1. 메인에서 바로구매 하기

![oneselect to Buy](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/oneselect%20to%20Buy.PNG)
![buy](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/buy.PNG)

현재 수량을 일부로 구분하기 위해 `7`로 추가하였고, 해당 상품이 구매하기로 넘어간 것을 확인할 수 있다.

2. 장바구니에서 구매하기

![allselectCart to Buy](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/allselectCart%20to%20Buy.PNG)
![allbuy](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/allbuy.PNG)

장바구니에 있는 모든 상품을 선택하고 해당상품이 구매하기로 넘어간 것을 확인할 수 있다.

 ##### *전체상품이 아닌 개별상품만 구매했을 경우 장바구니가 유지 되고 있음을 확인해보자.*

 ![cart1selecttoBuy](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/cart1selecttoBuy.PNG)
 ![DeleteCart](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/DeleteCart.PNG)

해당상품이 구매되고 화면에서 제거되었음을 확인할 수 있다.

###### 경고창 확인

![emptyCart](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/emptyCart.PNG)
먼저, 장바구니가 비어있음을 확인한다.

1. 색상을 설정하지 않고 장바구니 혹은 구매하기 버튼을 클릭하였을 경우

![noselectColor](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/noselectColor.PNG)

2. 아무런 상품을 선택하지 않고 구매하기를 클릭하였을 경우

![noselectItem](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/noselectItem.PNG)

3. 장바구니가 비어있는데 구매하기를 선택했을 경우

![buyEmptyCart](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/buyEmptyCart.PNG)

4. 개인정보를 완전하게 입력하지않고 구매했을 경우

![noInputErr](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/noInputErr.PNG)

모두 제대로 경고창이 뜨고있음을 확인할 수 있다.

마지막으로 모든 구매가 끝나고 완료하게 되면 다음과 같이 `Toast.Message`를 출력하고 메인으로 돌아가게 된다.

![buyToast](https://github.com/yannJu/AndroidStudioProject/blob/master/resultCapture/buyToast.PNG)
