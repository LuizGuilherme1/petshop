package com.br.unisales;

//import java.util.List;

//import com.br.unisales.configuration.ConfigurationManager;
//import com.br.unisales.service.PetService;
//import com.br.unisales.table.Pet;
import com.br.unisales.form.PetForm;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        //PetService service = new PetService();
        /*new ConfigurationManager();
        service.salvar(null, "mario", 50.0, 1.5, 7, "masculino", "gato", "persa");
        service.salvar(null, "maria", 40.0, 2.0, 10, "feminino", "labrador", "cachorro");
        List<Pet> list = service.listar();
        for(Pet item: list){
           System.out.println("Codigo: "+item.getId() + " Nome: " + item.getNome());
        }*/
        java.awt.EventQueue.invokeLater(() -> {
            new PetForm().setVisible(true);
        });
    }
}