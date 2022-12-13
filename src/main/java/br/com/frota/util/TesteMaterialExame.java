package br.com.frota.util;

import br.com.frota.model.MaterialExame;
import br.com.frota.servico.ServicoMaterialExame;

import java.sql.SQLException;
import java.util.List;

public class TesteMaterialExame {
    static final ServicoMaterialExame servicoMaterialExame = new ServicoMaterialExame();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(servicoMaterialExame.contar());

        //salvar
        MaterialExame materialExame = new MaterialExame("M-1", "O-1");
        servicoMaterialExame.salvar(materialExame);

        //buscar por ID
        long id = materialExame.getId();
        materialExame = servicoMaterialExame.buscarPorId(id);
        System.out.println(materialExame);

        //Update
        materialExame.setMaterial("M-2");
        materialExame.setObservacao("O-2");
        servicoMaterialExame.update(materialExame);
        materialExame = servicoMaterialExame.buscarPorId(id);
        System.out.println(materialExame);

        //Select all
        List<MaterialExame> materialExames = servicoMaterialExame.buscarTodos();
        materialExames.forEach(System.out::println);

        //Delete
        servicoMaterialExame.remover(id);
        servicoMaterialExame.buscarTodos().forEach(System.out::println);
    }
}
