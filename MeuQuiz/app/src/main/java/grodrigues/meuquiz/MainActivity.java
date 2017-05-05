package grodrigues.meuquiz;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.CompoundButton;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private TextView txtPergunta;

    private RadioButton opcaoA;
    private RadioButton opcaoB;
    private RadioButton opcaoC;
    private Button btnOk;


    String Perguntas[] = {"Primeira pergunta: ",
            "Segunda Pergunta",
            "Terceira Pergunta",
            "Quarta Pergunta",
            "Quinta Pergunta",};

    String OpcaoA[] = {"Resposta-> A primeira pergunta " ,
            "Resposta-> A segunda pergunta " ,
            "Resposta-> A terceira pergunta " ,
            "Resposta-> A quarta pergunta " ,
            "Resposta-> A quinta pergutna "};
    String OpcaoB[] = {"Resposta-> B primeira pergunta ",
            "Reposta-> B segunda pergunta " ,
            "Resposta-> B terceira pergunta ",
            "Resposta-> B quarta pergunta",
            "Resposta-> B quinta pergunta"};
    String OpcaoC[]  = {"Resposta-> C primeira pergunta" ,
            "Resposta-> C segunda pergunta" ,
            "Resposta-> C terceira pergunta",
            "Resposta-> C quarta pergunta",
            "Resposta-> C quinta pergunta"};

    int listaRespostas[] = new int[Perguntas.length];
    int listaGabarito[] = {1,2,3,1,2};
    int respostaCorreta = 0;
    int numeroPergunta = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setEnabled(false);



        txtPergunta = (TextView) findViewById(R.id.campoTexto);

        opcaoA = (RadioButton) findViewById(R.id.opcaoA);
        opcaoB = (RadioButton) findViewById(R.id.opcaoB);
        opcaoC = (RadioButton) findViewById(R.id.opcaoC);

        radioGroup = (RadioGroup) findViewById(R.id.grupoRadio);

        atualizaPerguntas(btnOk);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.opcaoA:
                        Log.d("s", "Opcao n1!");
                        listaRespostas[numeroPergunta-1] = 1;
                        break;

                    case R.id.opcaoB:
                        Log.d("s", "Opcao n2!");
                        listaRespostas[numeroPergunta-1] = 2;
                        break;

                    case R.id.opcaoC:
                        Log.d("s", "Opcao n3!");
                        listaRespostas[numeroPergunta-1] = 3;
                        break;
                }
                btnOk.setEnabled(true);
            }
        });

    }
    public void atualizaPerguntas(View view){


        if(numeroPergunta == Perguntas.length){

            opcaoA.setEnabled(false);
            opcaoB.setEnabled(false);
            opcaoC.setEnabled(false);
            radioGroup.clearCheck();
            confereResultado();
        }else{
            txtPergunta.setText(Perguntas[numeroPergunta]);
            opcaoA.setText(OpcaoA[numeroPergunta]);
            opcaoB.setText(OpcaoB[numeroPergunta]);
            opcaoC.setText(OpcaoC[numeroPergunta]);

            numeroPergunta ++;
            btnOk.setEnabled(false);
            radioGroup.clearCheck();
        }


    }
    public void confereResultado(){
        int contadorLista = 0;
        for (int numero : listaRespostas){
            System.out.println(numero);
            if(numero==listaGabarito[contadorLista]){
                respostaCorreta++;
                System.out.println("Resposta Correta ");
            }else{
                System.out.println("Resposta incorreta ");
            }
            contadorLista++;
        }
        alertaResultado(btnOk);
    }
    public void alertaResultado(View view){
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Resultado ");
        alertDialog.setMessage("Voce acertou " + respostaCorreta + " questoes");
        alertDialog.show();
        btnOk.setEnabled(false);

    }
}
