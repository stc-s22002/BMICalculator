package jp.suntech.s22002.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //リスナ・オブジェクトの用意
        HelloListener listener = new HelloListener();

        //計算ボタンであるButtonオブジェクトを取得
        Button btCal = findViewById(R.id.btCal);
        //計算ボタンにリスナを設定
        btCal.setOnClickListener(listener);

        //クリアボタンであるButtonオブジェクトを取得
        Button btClear = findViewById(R.id.btClear);
        //クリアボタンにリスナを設定
        btClear.setOnClickListener(listener);
    }

    private class HelloListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            //名前入力欄であるedittextオブジェクトを取得

            EditText inputA = findViewById(R.id.etOld);
            EditText inputB = findViewById(R.id.etCm);
            EditText inputC = findViewById(R.id.etKg);

            TextView output1 = findViewById(R.id.tvOutput1);
            TextView output2 = findViewById(R.id.tvOutput2);
            TextView output3 = findViewById(R.id.tvOutput3);
            TextView output4 = findViewById(R.id.tvOutput4);



            //タップされた画面部分のidのRを取得
            int id = view.getId();

            //計算ボタンの場合
            if(id==R.id.btCal){
                //入力された名前文字列を取得

                float inputfloA = Float.parseFloat(inputA.getText().toString());
                float inputfloB = Float.parseFloat(inputB.getText().toString());
                float inputfloC = Float.parseFloat(inputC.getText().toString());

                //計算
                float BMI = inputfloC / ((inputfloB/100) * (inputfloB/100));
                double tekisei = (inputfloB/100) * (inputfloB/100) * 22;
                       tekisei = ((double) Math.round(tekisei * 10))/10;


                output1.setText("あなたの肥満度は");

                if(BMI >= 40){
                    output2.setText("肥満(4度)");
                }
                else if(BMI >= 35){
                    output2.setText("肥満(3度)");
                }
                else if(BMI >= 30){
                    output2.setText("肥満(2度)");
                }
                else if(BMI >= 25){
                    output2.setText("肥満(1度)");
                }
                else if(BMI < 18.5){
                    output2.setText("低体重");
                }
                else output2.setText("普通体重");

                output3.setText("あなたの適正体重は");
                output4.setText(tekisei + " kg");


                if(inputfloA < 16){
                    OrderConfirmDialogFragment dialogFragment = new OrderConfirmDialogFragment();
                    dialogFragment.show(getSupportFragmentManager(),"OrderConfirmDialogFragment");
                }
            }

            //クリアボタンの場合
            if(id==R.id.btClear) {
                //名前入力欄を空文字に設定
                inputA.setText("");
                inputB.setText("");
                inputC.setText("");
                output1.setText("");
                output2.setText("");
                output3.setText("");
                output4.setText("");
            }
        }
    }
}