package com.example.ejercicioencasaapp.models;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.views.ListaCompletaEjerciciosActivity;
import com.example.ejercicioencasaapp.views.MisPlanesFragment;
import com.example.ejercicioencasaapp.views.Plan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class NombreDialog extends AppCompatDialogFragment {
    private EditText editTextNombre;
    private NombreDialogListener listener;
    private Button btnCambiarImagen;
    private ImageView portada;
    private Drawable drawable;
    private Bitmap bitmap;
    private int intPortada;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.insertar_nombre_plan,null);

        builder.setView(view)
                .setTitle("Nombre")
                .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //se define el nombre del nuevo plan
                        String nombre = editTextNombre.getText().toString();
                        listener.applyText(nombre);
                        //se crea el nuevo plan
                        drawable = portada.getDrawable();


                        Plan plan = new Plan(nombre, 0,R.drawable.card_plan);
                        PlanDAO planDAO = new PlanDAO(getContext());
                        long idPlan = planDAO.insertarPlan(plan);
                        //convierto el id a int
                        int id = (int) idPlan;
                        if(id >-1){
                            Toast.makeText(getContext(), "Id = "+id,Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Fallo en la conexión",Toast.LENGTH_SHORT).show();
                        }
                        //Se inicia el activity con la lista de ejrcicios para agregar y se pasa el id del nuevo plan
                        Intent intent = new Intent(getContext(),ListaCompletaEjerciciosActivity.class);
                        intent.putExtra(ListaCompletaEjerciciosActivity.EXTRA_ID_PLAN,id);
                        getContext().startActivity(intent);
                    }
                });

        editTextNombre = view.findViewById(R.id.etPonerNombrePlan);
        portada = view.findViewById(R.id.ivPortada);

        btnCambiarImagen = view.findViewById(R.id.btnCambiarImagen);
        btnCambiarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });
        return builder.create();

    }
    //metodo para seleccionar una imagen del dispositivo
    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"seleccione imgen desde:"),10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Uri imagePath=data.getData();
            //portada.setImageURI(imagePath);

            try {
                InputStream inputStream = getContext().getContentResolver().openInputStream(imagePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                portada.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (NombreDialogListener) context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString() + "must implement NombreDialogListener");
        }
    }

    public interface NombreDialogListener{
        void applyText(String nombre);
    }
}
