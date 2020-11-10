package com.jef.hamburgueria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantidade = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void somar(View view) {
    quantidade = quantidade +1;
    displayQuantidade(quantidade);
    }

    public void subtrair(View view) {
        quantidade = quantidade -1;
        displayQuantidade(quantidade);
    }

    public void displayQuantidade(int qtdHambuerguer){
        TextView qtdTextview = (TextView) findViewById(R.id.quantidade_tv);
        qtdTextview.setText("" + qtdHambuerguer);
        }
    public void displayPedido(String pedido ) {
        TextView qtdTextview = (TextView) findViewById(R.id.resumo_pedido);
        qtdTextview.setText(pedido);

    }

    public void enviarPedido(View view) {
        EditText campoNome = (EditText) findViewById(R.id.campo_nome);
        String nome = campoNome.getText().toString();
        //checa se tem bacon
        CheckBox checkboxBacon = (CheckBox) findViewById(R.id.bacon);
        boolean temBacon = checkboxBacon.isChecked();

        //checa se tem Queijo
        CheckBox checkboxQueijo = (CheckBox) findViewById(R.id.queijo);
        boolean temQuiejo = checkboxQueijo.isChecked();

        //checa se tem bacon
        CheckBox checkboxOnion = (CheckBox) findViewById(R.id.onionrings);
        boolean temOnion = checkboxOnion.isChecked();

        int valor = calculaPreco(temBacon,temQuiejo,temOnion);

        String mensagem = "Nome: " + nome;
        mensagem += "\nBacon? " + temBacon;
        mensagem += "\nQueijo? " + temQuiejo;
        mensagem += "\nOnion Rings? " + temOnion;
        mensagem += "\nQuantidade:  " + quantidade;
        mensagem += "\nTotal:  R$" + valor;

        TextView pedidoTextView = (TextView) findViewById(R.id.resumo_pedido);
        pedidoTextView.setText(mensagem);

        Intent intent = new Intent(Intent.ACTION_SENDTO); // email
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pedido de " + nome);
        intent.putExtra(Intent.EXTRA_TEXT, mensagem);
        if(intent.resolveActivity(getPackageManager())!=null)
            startActivity(intent);

    }

    public int calculaPreco(boolean temBacon, boolean temQuijo, boolean temOnion){
      int precoBase = 20;
      int bacon = 2;
      int queijo = 3;
      int onion = 4;

      if (temBacon)
          precoBase += bacon;


        if (temQuijo)
            precoBase += queijo;


        if (temOnion)
            precoBase += onion;

        return precoBase * quantidade;

    }


}
