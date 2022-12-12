package br.com.frota.util;

import br.com.frota.DAO.MaterialExameDAO;
import br.com.frota.model.MaterialExame;
import br.com.frota.servico.ServicoMaterialExame;

import java.sql.SQLException;
import java.util.List;

public class TesteMaterialExame {
    static MaterialExameDAO materialExameDAO = new MaterialExameDAO();

    static ServicoMaterialExame servicoMaterialExame = new ServicoMaterialExame();

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
        materialExameDAO.updateMaterialExame(materialExame);
        materialExame = materialExameDAO.findById(id);
        System.out.println(materialExame);

        //Select all
        List<MaterialExame> materialExames = materialExameDAO.selectAllMaterialExame();
        materialExames.forEach(System.out::println);

        //Delete
        materialExameDAO.deleteMaterialExame(id);
        materialExameDAO.selectAllMaterialExame().forEach(System.out::println);
    }
}
