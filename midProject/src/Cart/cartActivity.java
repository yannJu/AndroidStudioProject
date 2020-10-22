package com.example.finalmidtermproject;

import androidx.appcompat.app.AlertDialog;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class cartActivity extends AppCompatActivity {
    Cart item; //item이 담긴 Cart type객체 (Cart안에 arraylist안에 String[]으로 담겨있음)
    ArrayList<Integer> cartNum = new ArrayList<Integer>(); //장바구니를 띄우기 위해 item의 idx를 담은 list ~> idx는 ck박스를 위해 존재
    ArrayList<Integer> buyNum = new ArrayList<Integer>(); //buyActivity로 넘길idx를 담는 list
    Button gotoBack, gotoBuy; //액티비티 전환을 위한 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Variable
        final CheckBox cartAllSelect; //선택시 모든 아이템들이 체크되도록 하는 체크박스
        int cartSize = 0; //장바구니가 비어있는지 확인하기 위한 변수

        LinearLayout lay, cartLay; //리니어 레이아웃을 동적으로 추가하기 위하여 사용하는 전체 레이아웃 mainLay와 동적 뷰 cartLay
        CheckBox itemCk; //동적으로 생성되는 레이아웃에 해당하는 체크박스 ~> 아이템의 idx를 id로 하여 사용
        ImageView itemImg; //동적으로 생성되는 레이아웃에 해당하는 img
        TextView itemTitle, itemOption, itemPrice, itemCnt, zero; //동적으로 생성되는 레이아웃에 해당하는 item명, 옵션, 가격, 수량, 장바구니가 비어있음을 나타내는 텍스트

        Intent getIntent = getIntent(); //Main에서 값을 받기 위한 Intent

        //getID
        //레이아웃에 관한 ID
        lay = (LinearLayout) findViewById(R.id.add_item);
        //Bt에 관한 ID ~> 액티비티 전환
        gotoBack = (Button) findViewById(R.id.gotoHome);
        gotoBuy = (Button) findViewById(R.id.gotoBuy);
        //전달받은 Intent에 관한 ID와 장바구니 크기 (비어있는지 CK)
        item = (Cart)getIntent.getSerializableExtra("cart");
        cartNum = getIntent.getExtras().getIntegerArrayList("cartNum");
        cartSize = cartNum.size();
        //장바구니가 비었을때 화면에 표시하기 위한 TextView에 대한 ID
        zero  = (TextView) findViewById(R.id.zero);
        //장바구니의 CK박스를 모두 CK하기 위한 체크박스에 대한 ID
        cartAllSelect = (CheckBox)findViewById(R.id.allSelect);

        final int finalCartSize = cartSize;
        final ArrayList<Integer> finalCartNum = cartNum;
        //장바구니의 모든 상품을 CK 했을 때
        cartAllSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox ck = new CheckBox(getApplicationContext());
                //전체 상품을 선택할 때
                if (cartAllSelect.isChecked()) {
                    //장바구니에 상품이 있는 경우
                    if (finalCartSize > 0) {
                        //Cart의 idx를 체크(idx == Ckbox의 ID)
                        for (int num : finalCartNum) {
                            ck = (CheckBox) findViewById(num);
                            ck.setChecked(true); //CKbox를 true로
                        }
                    }
                }
                //전체 상품을 선택 해제 하였을 때
                else{
                    //장바구니에 상품이 있는 경우
                    if (finalCartSize > 0) {
                        //Cart의 idx를 체크(idx == Ckbox의 ID)
                        for (int num : finalCartNum) {
                            ck = (CheckBox) findViewById(num);
                            ck.setChecked(false); //CKbox를 false로
                        }
                    }
                }
            }
        });

        //장바구니에 상품이 있는 경우
        if (cartSize > 0){
            //장바구니 상품의 idx로 접근하여 탐색
            for (int num:cartNum){
                //상품이 String[] = {Type, Title, Option, Price, Cnt}로 이루어져있음
                String[] obj = item.getItems(num);
                cartLay = new LinearLayout(this);
                //동적으로 추가하기 위한 레이아웃과 그에 대한 Parameter설정
                LinearLayout.LayoutParams cartParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                cartParam.weight=1;
                cartParam.height=240;

                //ItemCkBox생성, ID는 해당 상품의 idx로
                itemCk = new CheckBox(this);
                itemCk.setWidth(100);
                itemCk.setId(num);

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

                //CKbox 를 false로 초기화
                itemCk.setChecked(false);

                //Type(obj[0])에 따라 Img 설정
                if (Integer.parseInt(obj[0])==1){
                    itemImg.setImageResource(R.drawable.band);
                }
                else if (Integer.parseInt(obj[0])==2){
                    itemImg.setImageResource(R.drawable.circlepin);
                }
                else if(Integer.parseInt(obj[0])==3){
                    itemImg.setImageResource(R.drawable.squarepin);
                }

                //Title(obj[1]), Option(obj[2]), Price(obj[3]), Cnt(obj[4]) 설정
                itemTitle.setText(obj[1]);
                itemOption.setText("[옵션 : " + obj[2] + "]");
                itemPrice.setText(obj[3]);
                itemCnt.setText(obj[4]);

                //레이아웃에 추가
                cartLay.addView(itemCk);
                cartLay.addView(itemImg);
                cartLay.addView(itemTitle);
                cartLay.addView(itemOption);
                cartLay.addView(itemPrice);
                cartLay.addView(itemCnt);
                cartLay.setLayoutParams(cartParam);
                lay.addView(cartLay);
            }
        }

        //장바구니에 상품이 없는경우 TextView를 띄움
        else{
            zero.setVisibility(View.VISIBLE);
        }

        //돌아가기 버튼을 클릭하였을 때
        gotoBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //MainActivity에 Item, CartIdx의 값을 반환
                Intent backData = new Intent();
                backData.putExtra("backItem", item);
                backData.putExtra("backNum", finalCartNum);
                setResult(RESULT_OK, backData);
                finish();
            }
        });

        final ArrayList<Integer> finalCartNum1 = cartNum;
        gotoBuy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int ckInt = 0;
                for (int num: finalCartNum){
                    CheckBox ck = new CheckBox(getApplicationContext());
                    ck = (CheckBox) findViewById(num);

                    if (ck.isChecked()){
                        ckInt += 1;
                        buyNum.add(num);
                    }
                }
                if (ckInt == 0){
                    AlertDialog.Builder noItem = new AlertDialog.Builder(cartActivity.this);
                    noItem.setMessage("주문할 상품을 선택해 주세요!");
                    noItem.setPositiveButton("확인", null);
                    noItem.create().show();
                }
                else {
                    Intent buyData = new Intent(getApplicationContext(), buyActivity.class);
                    buyData.putExtra("buyItem", item);
                    buyData.putExtra("buyNum", buyNum);
                    startActivityForResult(buyData, 1);
                }
            }
        });
    }

    //cartActivity에서 값을 반환받았을 경우
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                for(int num:buyNum){
                    cartNum.remove(cartNum.indexOf(num));
                }
                buyNum = new ArrayList<Integer>();
                gotoBack.performClick();
            }

        }
    }
}
