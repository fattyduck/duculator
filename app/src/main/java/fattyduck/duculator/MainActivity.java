package fattyduck.duculator;

import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.math.BigDecimal;
import java.util.Locale;


public class MainActivity extends Activity {
    String express="";
    String answer ="";
    TextView panel;
    BigDecimal results;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener(){
            public void onInit(int status){
                if(status!= TextToSpeech.ERROR ){
                    tts.setLanguage(Locale.US);
                }
            }
        });

        panel =  (TextView)findViewById(R.id.panel);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(tts!=null){
            tts.stop();
            tts.shutdown();
        }
    }

    public boolean isNum(char c){

        return c=='0'||c=='1'||c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9';

    }
    public boolean isOp(char c){
        return c == '+'||c == '-'||c == '*'||c == '/'||c == '%'||c == '^';
    }

    public int numOfOpenParen(String s){
        int num =0;
        for(int i= 0; i<s.length();i++){
            if(s.charAt(i)=='('){
                num++;
            }
        }
        return num;
    }
    public int numOfCloseParen(String s){
        int num =0;
        for(int i= 0; i<s.length();i++){
            if(s.charAt(i)==')'){
                num++;
            }
        }
        return num;
    }


    public void buttonclick(View v){
        Button log = (Button)findViewById(R.id.log);
        Button sin = (Button)findViewById(R.id.sin);
        Button cos = (Button)findViewById(R.id.cos);
        Button tan = (Button)findViewById(R.id.tan);
        Button ln = (Button)findViewById(R.id.ln);
        Button sqrt = (Button)findViewById(R.id.sqrt);
        Button tip = (Button)findViewById(R.id.tip);
        String euler = String.valueOf(Math.E);

        switch (v.getId()) {
            case R.id.rad:
                express += "RAD(";
                break;
            case R.id.one:
                express += "1";
                break;
            case R.id.two:
                express += "2";
                break;
            case R.id.three:
                express += "3";
                break;
            case R.id.four:
                express += "4";
                break;
            case R.id.five:
                express += "5";
                break;
            case R.id.six:
                express += "6";
                break;
            case R.id.seven:
                express += "7";
                break;
            case R.id.eight:
                express += "8";
                break;
            case R.id.nine:
                express += "9";
                break;
            case R.id.zero:
                express += "0";
                break;
            case R.id.help:
                String dumb = "Only cows need help using a calculator, moo moo dumb cow!";
                tts.speak(dumb, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.openParen:
                express += "(";
                break;
            case R.id.closeParen:
                if (numOfOpenParen(express) > numOfCloseParen(express)) {
                    express += ")";
                }
                break;
            case R.id.modulo:
                if (express.length() > 0) {
                    if (!isOp(express.charAt(express.length() - 1))) {
                        express += "%";
                    } else {
                        express = express.substring(0, express.length() - 1) + "%";
                    }

                }
                break;
            case R.id.clear:
                express = "";
                break;
            case R.id.pi:
                express += "PI";
                break;
            case R.id.exp:
                express += "E";
                break;
            case R.id.power:
                if (express.length() == 0) {
                    express += "1^";
                } else {
                    if (!isOp(express.charAt(express.length() - 1))) {
                        express += "^";
                    } else {
                        express = express.substring(0, express.length() - 1) + "^";
                    }

                }
                break;
            case R.id.div:
                if (express.length() > 0) {
                    if (!isOp(express.charAt(express.length() - 1))) {
                        express += "/";
                    } else {
                        express = express.substring(0, express.length() - 1) + "/";
                    }

                }
                break;
            case R.id.times:
                if (express.length() == 0) {
                    express += "1*";
                } else {
                    if (!isOp(express.charAt(express.length() - 1))) {
                        express += "*";
                    } else {
                        express = express.substring(0, express.length() - 1) + "*";
                    }

                }
                break;
            case R.id.minus:
                if (express.length() > 0) {
                    if (!isOp(express.charAt(express.length() - 1))) {
                        express += "-";
                    } else {
                        express = express.substring(0, express.length() - 1) + "-";
                    }

                }
                break;
            case R.id.plus:
                if (express.length() == 0) {
                    express += "0+";
                } else {
                    if (!isOp(express.charAt(express.length() - 1))) {
                        express += "+";
                    } else {
                        express = express.substring(0, express.length() - 1) + "+";
                    }

                }
                break;
            case R.id.point:
                if (express.length() == 0) {
                    express += "0.";
                } else {
                    if (isNum(express.charAt(express.length() - 1))) {
                        express += ".";
                    } else if(isOp(express.charAt(express.length() - 1))) {
                        express += "0.";
                    }

                }
                break;
            case R.id.e:

                express += euler;
                break;
            case R.id.perm:
                express += "!";
                break;
            case R.id.inv:
                if (cos.getText().toString().equals("cos")) {
                    sin.setText("asin");
                    cos.setText("acos");
                    tan.setText("atan");
                    ln.setText("ex");
                    log.setText("10x");
                    sqrt.setText("x2");
                    tip.setText("total");
                } else {
                    sin.setText("sin");
                    cos.setText("cos");
                    tan.setText("tan");
                    ln.setText("ln");
                    log.setText("log");
                    sqrt.setText("sqrt");
                    tip.setText("tip");
                }
                break;
            case R.id.sin:
                if (sin.getText().toString().equals("sin")) {
                    express += "SIN(";
                } else {
                    express += "ASIN(";
                }
                break;
            case R.id.cos:
                if (cos.getText().toString().equals("cos")) {
                    express += "COS(";
                } else {
                    express += "ACOS(";
                }
                break;
            case R.id.tan:
                if (tan.getText().toString().equals("tan")) {
                    express += "TAN(";
                } else {
                    express += "ATAN(";
                }
                break;
            case R.id.ln:
                if (ln.getText().toString().equals("ln")) {
                    express += "LOG(";
                } else {
                    express += euler + "^";
                }
                break;
            case R.id.log:
                if (log.getText().toString().equals("log")) {
                    express += "LOG10(";
                } else {
                    express += "10^";
                }
                break;
            case R.id.sqrt:
                if (sqrt.getText().toString().equals("sqrt")) {
                    express += "SQRT(";
                } else {
                    if (express.length() > 0 && isNum(express.charAt(express.length() - 1))) {
                        express += euler + "^2";
                    }
                }
                break;
            case R.id.tip:
                if (tip.getText().toString().equals("tip")) {
                    if (express.length() > 0) {
                        if (isNum(express.charAt(express.length() - 1))) {
                            express += "*0.20";
                        } else {
                            express += "0.20*";
                        }
                    } else {
                        express += "0.20*";
                    }

                } else if (tip.getText().toString().equals("total")) {
                    if (express.length() > 0) {
                        if (isNum(express.charAt(express.length() - 1))) {
                            express += "*1.20";
                        } else {
                            express += "1.20*";
                        }
                    } else {
                        express += "1.20*";
                    }
                }
                break;
        }



        panel.setText(express);

        if(v.getId()==R.id.equals){
            try{

                if(express.charAt(express.length()-1)=='+'||express.charAt(express.length()-1)=='-'){
                    express+=0;
                }
                if(express.charAt(express.length()-1)=='*'||express.charAt(express.length()-1)=='/'||express.charAt(express.length()-1)=='^'||express.charAt(express.length()-1)=='%'){
                    express+=1;
                }
                if(numOfOpenParen(express)>numOfCloseParen(express)){
                    int missing = numOfOpenParen(express)-numOfCloseParen(express);
                    for(int i = 0; i<missing; i++){
                        express+=")";
                    }
                }
                Expression expression = new Expression(express);
                results = expression.eval();
                answer = String.valueOf(results.doubleValue());
                panel.setText(answer);
                tts.speak(answer, TextToSpeech.QUEUE_FLUSH, null );

            }catch (Exception e){
                panel.setText("ERROR!");

                tts.speak("ERROR!", TextToSpeech.QUEUE_FLUSH, null );
            }
        }
    }

}
