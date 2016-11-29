package com.igormelo.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.textView);
        Observable<String> myObservable = Observable.just("Hello");// Emite hello
        Observer<String> myObserver = new Observer<String>() {
            @Override
            public void onCompleted() {
                //Chamado quando o observable nao tiver mais dados para emitir
            }

            @Override
            public void onError(Throwable e) {
                //Chamado quando o observable encontrar erros

            }

            @Override
            public void onNext(String s) {
                //Chamado cada vez que o observable emitir os dados
                Toast.makeText(MainActivity.this, "OBSERVER ->" + s, Toast.LENGTH_SHORT).show();
                textView.setText(s);
            }
        };
        //TODO esse metodo (Subscribe) atribui o observer a um observable ou seja, faz o Observer enxergar o Observable
        Subscription mySubscription1 = myObservable.subscribe(myObserver);


        //A interface Action é usada pois o metodo Oncompleted e OnError nao sao muito usados, entao é mais simples usar o action que contem um metodo chamado call
        Action1<String> myAction = new Action1<String>() {
            @Override
            public void call(String s) {
                textView.setText(s);
            }
        };
        Subscription mySubscription = myObservable.subscribe(myAction);
    }
}
