package com.example.finalmidtermproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.security.acl.Group;
import java.util.ArrayList;

public class buyActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        //Variable
        Intent getIntent = getIntent();
        ImageView buyImg;
        TextView buyTitle, buyOption, buyPrice, buyCnt, buyResult, finalResult, receiver, address, call, email, message;
        final EditText name, mainAdr, phoneNum, emailAdr, mes;
        String intentTitle, intentOption;
        int intentPrice, intentCnt, resultPrice;
        Boolean ckBand, ckCirclePin, ckSquarePin, ckMain;
        Cart cart;
        String[] buyItem = new String[0];
        Button buyBt;
        ArrayList<Integer> buyNum = new ArrayList<Integer>();
        GridLayout buyLay, nameLay, addressLay, phoneLay, emailLay, messageLay;

        ckMain = getIntent.getExtras().getBoolean("mainAc");
        ckBand = getIntent.getExtras().getBoolean("ckCartBand");
        ckCirclePin = getIntent.getExtras().getBoolean("ckCartCirclePin");
        ckSquarePin = getIntent.getExtras().getBoolean("ckCartSquarePin");

        cart = (Cart)getIntent.getSerializableExtra("buyItem");
        if (ckMain == false) {
            buyNum = getIntent.getExtras().getIntegerArrayList("buyNum");
        }
        finalResult = (TextView) findViewById(R.id.resultPrice);
        buyLay = (GridLayout) findViewById(R.id.add_cart);
        nameLay = (GridLayout) findViewById(R.id.name);
        addressLay = (GridLayout) findViewById(R.id.address);
        phoneLay = (GridLayout) findViewById(R.id.phone);
        emailLay = (GridLayout) findViewById(R.id.email);
        messageLay = (GridLayout) findViewById(R.id.message);

        buyBt = (Button) findViewById(R.id.finalBuy);

        resultPrice = 0;
        if (ckBand){
            //동적으로 추가하기 위한 레이아웃과 그에 대한 Parameter설정
            GridLayout lay = new GridLayout(this);

            //Item Img, Title, Option, Price, Cnt 생성
            buyImg = new ImageView(this);
            buyTitle = new TextView(this);
            buyOption = new TextView(this);
            buyPrice = new TextView(this);
            buyCnt = new TextView(this);
            buyResult = new TextView(this);

            buyImg.setImageResource(R.drawable.band);
            intentTitle = getIntent.getExtras().getString("bandTitle");
            intentOption = getIntent.getExtras().getString("bandColor");
            intentPrice = getIntent.getExtras().getInt("bandPrice");
            intentCnt = getIntent.getExtras().getInt("bandCnt");

            buyTitle.setText(intentTitle);
            buyOption.setText("[옵션 : " + intentOption + "]");
            buyPrice.setText("" + intentPrice);
            buyCnt.setText("" + intentCnt);
            buyResult.setText("" + intentCnt * intentPrice);

            GridLayout.Spec MrowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
            GridLayout.Spec McolSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
            GridLayout.LayoutParams MgridParam = new GridLayout.LayoutParams(MrowSpan, McolSpan);
            MgridParam.height = 240;

            lay.addView(buyImg, 200, 210);
            lay.addView(buyTitle, 190, 210);
            lay.addView(buyOption, 210, 210);
            lay.addView(buyPrice, 200, 210);
            lay.addView(buyCnt, 140, 210);
            lay.addView(buyResult, 200, 210);
            buyLay.addView(lay, MgridParam);

            resultPrice += intentPrice * intentCnt;
        }

        if (ckCirclePin){
            //동적으로 추가하기 위한 레이아웃과 그에 대한 Parameter설정
            GridLayout lay = new GridLayout(this);

            //Item Img, Title, Option, Price, Cnt 생성
            buyImg = new ImageView(this);
            buyTitle = new TextView(this);
            buyOption = new TextView(this);
            buyPrice = new TextView(this);
            buyCnt = new TextView(this);
            buyResult = new TextView(this);

            buyImg.setImageResource(R.drawable.circlepin);
            intentTitle = getIntent.getExtras().getString("circlePinTitle");
            intentOption = getIntent.getExtras().getString("circlePinColor");
            intentPrice = getIntent.getExtras().getInt("circlePinPrice");
            intentCnt = getIntent.getExtras().getInt("circlePinCnt");

            buyTitle.setText(intentTitle);
            buyOption.setText("[옵션 : " + intentOption + "]");
            buyPrice.setText("" + intentPrice);
            buyCnt.setText("" + intentCnt);
            buyResult.setText("" + intentCnt * intentPrice);

            GridLayout.Spec MrowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
            GridLayout.Spec McolSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
            GridLayout.LayoutParams MgridParam = new GridLayout.LayoutParams(MrowSpan, McolSpan);
            MgridParam.height = 240;

            lay.addView(buyImg, 200, 210);
            lay.addView(buyTitle, 190, 210);
            lay.addView(buyOption, 210, 210);
            lay.addView(buyPrice, 200, 210);
            lay.addView(buyCnt, 140, 210);
            lay.addView(buyResult, 200, 210);
            buyLay.addView(lay, MgridParam);

            resultPrice += intentPrice * intentCnt;
        }

        if (ckSquarePin) {
            //동적으로 추가하기 위한 레이아웃과 그에 대한 Parameter설정
            GridLayout lay = new GridLayout(this);

            //Item Img, Title, Option, Price, Cnt 생성
            buyImg = new ImageView(this);
            buyTitle = new TextView(this);
            buyOption = new TextView(this);
            buyPrice = new TextView(this);
            buyCnt = new TextView(this);
            buyResult = new TextView(this);

            buyImg.setImageResource(R.drawable.squarepin);
            intentTitle = getIntent.getExtras().getString("squarePinTitle");
            intentOption = getIntent.getExtras().getString("squarePinColor");
            intentPrice = getIntent.getExtras().getInt("squarePinPrice");
            intentCnt = getIntent.getExtras().getInt("squarePinCnt");

            buyTitle.setText(intentTitle);
            buyOption.setText("[옵션 : " + intentOption + "]");
            buyPrice.setText("" + intentPrice);
            buyCnt.setText("" + intentCnt);
            buyResult.setText("" + intentCnt * intentPrice);

            GridLayout.Spec MrowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
            GridLayout.Spec McolSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
            GridLayout.LayoutParams MgridParam = new GridLayout.LayoutParams(MrowSpan, McolSpan);
            MgridParam.height = 240;


            lay.addView(buyImg, 200, 210);
            lay.addView(buyTitle, 190, 210);
            lay.addView(buyOption, 210, 210);
            lay.addView(buyPrice, 200, 210);
            lay.addView(buyCnt, 140, 210);
            lay.addView(buyResult, 200, 210);
            buyLay.addView(lay, MgridParam);

            resultPrice += intentPrice * intentCnt;
        }

        if (buyNum.size() > 0){
            for (int num:buyNum){
                //동적으로 추가하기 위한 레이아웃과 그에 대한 Parameter설정
                GridLayout lay = new GridLayout(this);
                buyItem = cart.getItems(num);

                //Item Img, Title, Option, Price, Cnt 생성
                buyImg = new ImageView(this);
                buyTitle = new TextView(this);
                buyOption = new TextView(this);
                buyPrice = new TextView(this);
                buyCnt = new TextView(this);
                buyResult = new TextView(this);

                if (Integer.parseInt(buyItem[0]) == 1){
                    buyImg.setImageResource(R.drawable.band);
                }
                else if (Integer.parseInt(buyItem[0]) == 2){
                    buyImg.setImageResource(R.drawable.circlepin);
                }
                else if (Integer.parseInt(buyItem[0]) == 3){
                    buyImg.setImageResource(R.drawable.squarepin);
                }

                intentTitle = buyItem[1];
                intentOption = buyItem[2];
                intentPrice = Integer.parseInt(buyItem[3]);
                intentCnt = Integer.parseInt(buyItem[4]);

                buyTitle.setText(intentTitle);
                buyOption.setText("[옵션 : " + intentOption + "]");
                buyPrice.setText("" + intentPrice);
                buyCnt.setText("" + intentCnt);
                buyResult.setText("" + intentCnt * intentPrice);

                lay.addView(buyImg, 200, 210);
                lay.addView(buyTitle, 190, 210);
                lay.addView(buyOption, 210, 210);
                lay.addView(buyPrice, 200, 210);
                lay.addView(buyCnt, 140, 210);
                lay.addView(buyResult, 200, 210);

                GridLayout.Spec MrowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
                GridLayout.Spec McolSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
                GridLayout.LayoutParams MgridParam = new GridLayout.LayoutParams(MrowSpan, McolSpan);
                MgridParam.height = 240;

                buyLay.addView(lay, MgridParam);
                resultPrice += intentCnt * intentPrice;
            }
        }
        finalResult.setText("[상품구매금액 " + resultPrice + " + 배송비 2,500 = TOTAL " + (resultPrice + 2500) + "]");

        receiver = new TextView(this);
        name = new EditText(this);
        receiver.setText("받으시는 분");

        address = new TextView(this);
        mainAdr = new EditText(this);
        address.setText("주소");

        call = new TextView(this);
        phoneNum = new EditText(this);
        call.setText("휴대전화");

        email = new TextView(this);
        emailAdr = new EditText(this);
        email.setText("이메일");

        message = new TextView(this);
        mes = new EditText(this);
        message.setText("배송메세지");

        GridLayout.Spec MrowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
        GridLayout.Spec McolSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
        GridLayout.LayoutParams MgridParam = new GridLayout.LayoutParams(MrowSpan, McolSpan);
        MgridParam.height = 120;

        nameLay.addView(receiver, 200, 120);
        nameLay.addView(name, 500, 120);

        addressLay.addView(address, 200, 120);
        addressLay.addView(mainAdr, 1000, 120);

        phoneLay.addView(call, 200, 120);
        phoneLay.addView(phoneNum, 500, 120);

        emailLay.addView(email, 200, 120);
        emailLay.addView(emailAdr, 500, 120);

        messageLay.addView(message, 200, 120);
        messageLay.addView(mes, 500, 120);

        final String[] finalBuyItem = buyItem;
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
    }
}
