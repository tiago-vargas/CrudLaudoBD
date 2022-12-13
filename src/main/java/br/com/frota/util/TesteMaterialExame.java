package br.com.frota.util;

import br.com.frota.DAO.MaterialExameDAO;
import br.com.frota.model.MaterialExame;
import br.com.frota.servico.ServicoMaterialExame;

import java.sql.SQLException;
import java.util.List;

public class TesteMaterialExame {
    static final MaterialExameDAO materialExameDAO = new MaterialExameDAO();

    static final ServicoMaterialExame servicoMaterialExame = new ServicoMaterialExame();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(materialExameDAO.count());

        //salvar
        MaterialExame materialExame = new MaterialExame("Material", "Observacao");
        servicoMaterialExame.salvar(materialExame);
        long id = materialExame.getId();

        //buscar por ID
        materialExame = materialExameDAO.findById(id);
        System.out.println(materialExame);

        //Update
        materialExame.setMaterial("Material-2");
        materialExame.setObservacao("Observacao-2");
        materialExameDAO.update(materialExame);
        materialExame = materialExameDAO.findById(id);
        System.out.println(materialExame);

        //Select all
        List<MaterialExame> materialExames = materialExameDAO.selectAll();
        materialExames.forEach(System.out::println);

        //Delete
        materialExameDAO.delete(id);
        materialExameDAO.selectAll().forEach(System.out::println);
    }
}
