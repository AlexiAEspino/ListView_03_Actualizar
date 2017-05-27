package com.example.alexae.demo_07_alexi_02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Button btMain;
    private int REQUEST_CODE=1;

    private ListView lvMain;
    private LVMainAdapter mLVMainAdapter;
    private int posi = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain= (ListView) findViewById(R.id.lvMain);

        btMain= (Button) findViewById(R.id.btMain);
        btMain.setOnClickListener(btMainOnClickListener);
        lvMain.setOnItemClickListener(lvMainOnItemOnCLickListener);


        mLVMainAdapter= new LVMainAdapter(MainActivity.this);
        lvMain.setAdapter(mLVMainAdapter);

    }

    private final AdapterView.OnItemClickListener lvMainOnItemOnCLickListener= new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Persona persona= mLVMainAdapter.getItem(position);

            Intent intent= new Intent(MainActivity.this,SecondActtivity.class);
            posi=position;

           intent.putExtra("codigo",persona.getId().toString());
            intent.putExtra("nombre",persona.getNombre().toString());
            intent.putExtra("apellido",persona.getApellido().toString());
            intent.putExtra("documento",persona.getDocumento().toString());
            intent.putExtra("edad",String.valueOf(persona.getEdad()));

            startActivityForResult(intent,99);

        }
    };

    private final View.OnClickListener btMainOnClickListener=new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            Intent intent= new Intent(MainActivity.this,SecondActtivity.class);

            startActivityForResult(intent,REQUEST_CODE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){

           if(requestCode==REQUEST_CODE){
               Persona persona= new Persona();

               persona.setId(java.util.UUID.randomUUID().toString());
               persona.setNombre(data.getStringExtra("nombre"));
               persona.setApellido(data.getStringExtra("apellido"));
               persona.setDocumento(data.getStringExtra("documento"));
               persona.setEdad(Integer.valueOf(data.getStringExtra("edad")));

               mLVMainAdapter.add(persona);

           }else if(requestCode==99){

               Persona persona = mLVMainAdapter.getItem(posi);

               mLVMainAdapter.remove(persona);

               persona.setId(data.getStringExtra("codigo"));
               persona.setNombre(data.getStringExtra("nombre"));
               persona.setApellido(data.getStringExtra("apellido"));
               persona.setDocumento(data.getStringExtra("documento"));
               persona.setEdad(Integer.valueOf(data.getStringExtra("edad")));

               mLVMainAdapter.insert(persona,posi);
               mLVMainAdapter.notifyDataSetChanged();
           }

        }
  /*
        if(requestCode==REQUEST_CODE){
            if(resultCode==RESULT_OK){

                Persona persona= new Persona();

                persona.setId(java.util.UUID.randomUUID().toString());
                persona.setNombre(data.getStringExtra("nombre"));
                persona.setApellido(data.getStringExtra("apellido"));
                persona.setDocumento(data.getStringExtra("documento"));
                persona.setEdad(Integer.valueOf(data.getStringExtra("edad")));

                mLVMainAdapter.add(persona);

            }else if(requestCode==99){



                Persona persona = mLVMainAdapter.getItem(posi);

                mLVMainAdapter.remove(persona);

                persona.setId(data.getStringExtra("codigo"));
                persona.setNombre(data.getStringExtra("nombre"));
                persona.setApellido(data.getStringExtra("apellido"));
                persona.setDocumento(data.getStringExtra("documento"));
                persona.setEdad(Integer.valueOf(data.getStringExtra("edad")));

                mLVMainAdapter.insert(persona,posi);
                mLVMainAdapter.notifyDataSetChanged();


            }
        }
*/
    };

}
