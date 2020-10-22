package com.example.finalmidtermproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView bandTxt, circlePinTxt, squarePinTxt, selectedText, circleSelectedTxt, squareSelectedTxt, setCnt, setCircleCnt, setSquareCnt, bandPrice, circlePinPrice, squarePinPrice; //상품명, 선택컬러명, 수량, 가격에 대한 txtview
    Spinner spinner, circleSpinner, squareSpinner; //색상을 선택하기 위한 Spinner
    String cntStr, circleStr, squareStr; //수량이 textview이므로 string으로 받는 변수
    int cntInt, circleInt, squareInt, allCnt = 0; //수량을 변경하기 위해 String을 int로 변경하여 값을 변경하기 위해 사용하는 변수
    Button cntPBt, cntMBt, cntPBtCircle, cntMBtCircle, cntPBtSquare, cntMBtSquare, allSelect, gotoBasket, gotoBuy; //수량 버튼(plus, minus), 전체 선택을 하는 버튼, 장바구니버튼, 구매하기버튼
    CheckBox ckBand, ckCirclePin, ckSquarePin; //체크박스를 set하기 위한 변수
    ArrayList<Integer> cartNum = new ArrayList<Integer>(); //cart의 Idx를 담는 ArrayList
    Cart c = new Cart(); //Item을 담는 Cart타입의 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView객체의 변수들 _ 상품명, 수량, 컬러, 가격
        //Band
        bandTxt = (TextView) findViewById(R.id.bandTitle);
        selectedText = (TextView) findViewById(R.id.colorTxt);
        setCnt = (TextView) findViewById(R.id.cntTxt);
        bandPrice = (TextView) findViewById(R.id.bandPrice);
        //CirclePin
        circlePinTxt = (TextView) findViewById(R.id.circlePinTitle);
        circleSelectedTxt = (TextView) findViewById(R.id.circlePinColorTxt);
        setCircleCnt = (TextView) findViewById(R.id.circlePinCntTxt);
        circlePinPrice = (TextView) findViewById(R.id.circlePinPrice);
        //SquarePin
        squarePinTxt = (TextView) findViewById(R.id.SquarePinTitle);
        squareSelectedTxt = (TextView) findViewById(R.id.SquarePinColorTxt);
        setSquareCnt = (TextView) findViewById(R.id.SquarePinCntTxt);
        squarePinPrice = (TextView) findViewById(R.id.SquarePinPrice);

        //Spinner객체과 plus, minus버튼
        spinner = (Spinner) findViewById(R.id.colorSpinner);
        cntPBt = (Button) findViewById(R.id.cntPlusBt);
        cntMBt = (Button) findViewById(R.id.cntMinusBt);
        circleSpinner = (Spinner) findViewById(R.id.circlePinColorSpinner);
        cntPBtCircle = (Button) findViewById(R.id.circlePinCntPlusBt);
        cntMBtCircle = (Button) findViewById(R.id.circlePinCntMinusBt);
        squareSpinner = (Spinner) findViewById(R.id.SquarePinColorSpinner);
        cntPBtSquare = (Button) findViewById(R.id.SquarePinCntPlusBt);
        cntMBtSquare = (Button) findViewById(R.id.SquarePinCntMinusBt);

        //수량을 담는 String, Int변수
        cntStr = setCnt.getText().toString();
        cntInt = Integer.parseInt(cntStr);
        circleStr = setCircleCnt.getText().toString();
        circleInt = Integer.parseInt(circleStr);
        squareStr = setSquareCnt.getText().toString();
        squareInt = Integer.parseInt(squareStr);

        //체크박스
        ckBand = (CheckBox) findViewById(R.id.selectCK_Band);
        ckCirclePin = (CheckBox) findViewById(R.id.selectCK_CirclePin);
        ckSquarePin = (CheckBox) findViewById(R.id.selectCK_SquarePin);

        //전체선택 버튼
        allSelect = (Button) findViewById(R.id.allSelect);

        //화면전환
        gotoBasket = (Button)findViewById(R.id.basketBt);
        gotoBuy = (Button)findViewById(R.id.buyBt);

        //ButtonEvent
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
        //CirclePinItem Plus, Minus
        cntPBtCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                circleInt += 1;
                setCircleCnt.setText(String.valueOf(circleInt));
            }
        });
        cntMBtCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (circleInt > 1) {circleInt -= 1;}
                setCircleCnt.setText(String.valueOf(circleInt));
            }
        });
        //SquarePinItem Plus, Minus
        cntPBtSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                squareInt += 1;
                setSquareCnt.setText(String.valueOf(squareInt));
            }
        });
        cntMBtSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (squareInt > 1) {squareInt -= 1;}
                setSquareCnt.setText(String.valueOf(squareInt));
            }
        });
        //AllSelectBT Event
        allSelect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //If CKBox is All True, All Make CKBox False
                if (ckBand.isChecked() == true && ckCirclePin.isChecked() == true && ckSquarePin.isChecked() == true){
                    ckBand.setChecked(false);
                    ckCirclePin.setChecked(false);
                    ckSquarePin.setChecked(false);
                }
                //If Button is Clicked, make all CkBox True
                else {
                    ckBand.setChecked(true);
                    ckCirclePin.setChecked(true);
                    ckSquarePin.setChecked(true);
                }
            }
        });
        //CartBT Event
        gotoBasket.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent cartActivity = new Intent(MainActivity.this, cartActivity.class);
                if (ckBand.isChecked()==true){
                    if (selectedText.getText().equals("색상선택")){
                        AlertDialog.Builder noOpBand = new AlertDialog.Builder(MainActivity.this);
                        noOpBand.setMessage("색상을 선택해 주세요!");
                        noOpBand.setPositiveButton("확인", null);
                        noOpBand.create().show();
                        return;
                    }
                    else {
                        cartNum.add(allCnt++);
                        c.addCart(1, (String) bandTxt.getText(), (String) selectedText.getText(), Integer.parseInt((String)bandPrice.getText()), cntInt);
                    }
                }

                if (ckCirclePin.isChecked()==true){
                    if (circleSelectedTxt.getText().equals("색상선택")){
                        AlertDialog.Builder noOpBand = new AlertDialog.Builder(MainActivity.this);
                        noOpBand.setMessage("색상을 선택해 주세요!");
                        noOpBand.setPositiveButton("확인", null);
                        noOpBand.create().show();
                        return;
                    }
                    else {
                        cartNum.add(allCnt++);
                        c.addCart(2, (String) circlePinTxt.getText(), (String) circleSelectedTxt.getText(), Integer.parseInt((String)circlePinPrice.getText()), circleInt);
                    }
                }
                if (ckSquarePin.isChecked()==true){
                    if (squareSelectedTxt.getText().equals("색상선택")){
                        AlertDialog.Builder noOpBand = new AlertDialog.Builder(MainActivity.this);
                        noOpBand.setMessage("색상을 선택해 주세요!");
                        noOpBand.setPositiveButton("확인", null);
                        noOpBand.create().show();
                        return;
                    }
                    else {
                        cartNum.add(allCnt++);
                        c.addCart(3, (String) squarePinTxt.getText(), (String) squareSelectedTxt.getText(), Integer.parseInt((String)squarePinPrice.getText()), squareInt);
                    }
                }
                cartActivity.putExtra("cart", c);
                cartActivity.putExtra("cartNum", cartNum);
                startActivityForResult(cartActivity, 0);

                //초기화
                ckBand.setChecked(false);
                ckCirclePin.setChecked(false);
                ckSquarePin.setChecked(false);

                cntInt = 1;
                setCnt.setText("1");
                circleInt = 1;
                setCircleCnt.setText("1");
                squareInt = 1;
                setSquareCnt.setText("1");

                spinner.setSelection(0);
                circleSpinner.setSelection(0);
                squareSpinner.setSelection(0);
            }
        });

        //BuyBt Event
        gotoBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buyActivity = new Intent(getApplicationContext(), buyActivity.class);
                if (ckBand.isChecked()==false && ckCirclePin.isChecked()==false && ckSquarePin.isChecked()==false){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("선택한 상품이 없습니다");
                    builder.setPositiveButton("확인", null);
                    builder.create().show();
                    return;
                }

                if (ckBand.isChecked()==true){
                    if (selectedText.getText().equals("색상선택")){
                        AlertDialog.Builder noOpBand = new AlertDialog.Builder(MainActivity.this);
                        noOpBand.setMessage("색상을 선택해 주세요!");
                        noOpBand.setPositiveButton("확인", null);
                        noOpBand.create().show();
                        return;
                    }
                    else {
                        buyActivity.putExtra("bandTitle", bandTxt.getText());
                        buyActivity.putExtra("ckCartBand", true);
                        buyActivity.putExtra("bandCnt", cntInt);
                        buyActivity.putExtra("bandColor", selectedText.getText());
                        buyActivity.putExtra("bandPrice", Integer.parseInt((String) bandPrice.getText()));
                    }
                }

                if (ckCirclePin.isChecked()==true){
                    if (circleSelectedTxt.getText().equals("색상선택")){
                        AlertDialog.Builder noOpBand = new AlertDialog.Builder(MainActivity.this);
                        noOpBand.setMessage("색상을 선택해 주세요!");
                        noOpBand.setPositiveButton("확인", null);
                        noOpBand.create().show();
                        return;
                    }
                    else {
                        buyActivity.putExtra("circlePinTitle", circlePinTxt.getText());
                        buyActivity.putExtra("ckCartCirclePin", true);
                        buyActivity.putExtra("circlePinCnt", circleInt);
                        buyActivity.putExtra("circlePinColor", circleSelectedTxt.getText());
                        buyActivity.putExtra("circlePinPrice", Integer.parseInt((String) circlePinPrice.getText()));
                    }
                }
                if (ckSquarePin.isChecked()==true){
                    if (squareSelectedTxt.getText().equals("색상선택")){
                        AlertDialog.Builder noOpBand = new AlertDialog.Builder(MainActivity.this);
                        noOpBand.setMessage("색상을 선택해 주세요!");
                        noOpBand.setPositiveButton("확인", null);
                        noOpBand.create().show();
                        return;
                    }
                    else {
                        buyActivity.putExtra("squarePinTitle", squarePinTxt.getText());
                        buyActivity.putExtra("ckCartSquarePin", true);
                        buyActivity.putExtra("squarePinCnt", squareInt);
                        buyActivity.putExtra("squarePinColor", squareSelectedTxt.getText());
                        buyActivity.putExtra("squarePinPrice", Integer.parseInt((String) squarePinPrice.getText()));
                    }
                }
                buyActivity.putExtra("mainAc", true);
                startActivity(buyActivity);

                //초기화
                ckBand.setChecked(false);
                ckCirclePin.setChecked(false);
                ckSquarePin.setChecked(false);

                cntInt = 1;
                setCnt.setText("1");
                circleInt = 1;
                setCircleCnt.setText("1");
                squareInt = 1;
                setSquareCnt.setText("1");

                spinner.setSelection(0);
                circleSpinner.setSelection(0);
                squareSpinner.setSelection(0);
            }
        });

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
        //CirclePin Color Spinner Event
        circleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                circleSelectedTxt.setText("" + parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){
                circleSelectedTxt.setText("색상선택");
            }
        });
        //SquarePin Color Spinner Event
        squareSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                squareSelectedTxt.setText("" + parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){
                squareSelectedTxt.setText("색상선택");
            }
        });
    }

    //cartActivity에서 값을 반환받았을 경우
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                c = (Cart) data.getSerializableExtra("backItem");
                cartNum = data.getExtras().getIntegerArrayList("backNum");
                allCnt = c.getSize();
            }
        }
    }
}