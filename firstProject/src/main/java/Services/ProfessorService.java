package Services;

import Dao.GenericDao;
import Model.ClassModel;
import Model.ProfessorModel;

import java.util.List;
import java.util.Optional;

public class ProfessorService {
    private GenericDao<ProfessorModel> genericDao = new GenericDao<>();

    public void addProfessor(ProfessorModel professorModel){
        genericDao.add(professorModel);
    }

    public List<ProfessorModel> getProfessors(ProfessorModel professorModel){
        List<ProfessorModel> myProfessors = genericDao.getAll(professorModel);
        return myProfessors;
    }

    public Optional<ProfessorModel> findByIdOptional(ProfessorModel professorModel , int id){
        List<ProfessorModel> professorModelList = genericDao.getAll(professorModel);
        return professorModelList.stream().filter(professorModel1 -> professorModel1.getCnp() == id).findFirst();
    }

    public ProfessorModel findById(ProfessorModel professorModel , int id){
        ProfessorModel professorModel1 = genericDao.findById(professorModel, id);
        return professorModel1;
    }

    public void updateProfessors(ProfessorModel professorModel){
        genericDao.update(professorModel);
    }

    public void deleteProfessors(ProfessorModel professorModel){
        genericDao.delete(professorModel);
    }
}
