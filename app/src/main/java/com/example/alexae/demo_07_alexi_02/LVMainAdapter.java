package com.example.alexae.demo_07_alexi_02;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alex.ae on 26/05/2017.
 */

public class LVMainAdapter extends ArrayAdapter<Persona>{



    public LVMainAdapter(Context context) {

        super(context,0, new ArrayList<Persona>());
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView ==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.main_item,parent,false);

        TextView tvMainItemFullName,tvMainItemDocumento,tvMainItemEdad;

        tvMainItemFullName = (TextView) convertView.findViewById(R.id.tvMainItemFullName);
        tvMainItemDocumento = (TextView) convertView.findViewById(R.id.tvMainItemDocumento);
        tvMainItemEdad= (TextView) convertView.findViewById(R.id.tvMainItemEdad);


        Persona persona= getItem(position);

        tvMainItemFullName.setText(persona.getNombre()+" "+ persona.getApellido());
        tvMainItemDocumento.setText(persona.getDocumento());
        tvMainItemEdad.setText( String.valueOf(persona.getEdad()));

        return convertView;


    }

    public Persona valor(int valor){


        Persona persona= getItem(valor);
        return  persona;
    };

}
