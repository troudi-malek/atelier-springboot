package tn.esprit.spring.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Entities.Universite;
import tn.esprit.spring.DAO.Repository.BlocRepository;
import tn.esprit.spring.DAO.Repository.ChambreRepository;
import tn.esprit.spring.DAO.Repository.FoyerRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class BlockService implements IBlocService{
    BlocRepository blocRepository;
    ChambreRepository chambreRepository;
    FoyerRepository foyerRepository;
    @Override
    public Bloc addBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public List<Bloc> addAll(List<Bloc> blocs) {
        return blocRepository.saveAll(blocs);
    }

    @Override
    public Bloc editBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public List<Bloc> findAll() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc findById(long id) {
        return blocRepository.findById(id).orElse(Bloc.builder().id(0).nomBloc("").build());
        //return blocRepository.findById(id).get();

    }

    @Override
    public void deleteById(long id) {
        blocRepository.deleteById(id);
    }

    @Override
    public void Delete(Bloc b) {
    Set<Chambre> chambres=b.getChambre();
       for(Chambre chambre:chambres){
           chambreRepository.delete(chambre);
       }
        blocRepository.delete(b);
    }

     @Override
     public Bloc findByNomBloc(String nomBloc) {
         return blocRepository.findByNomBloc(nomBloc);
     }

     @Override
     public List<Bloc> findByCapaciteBloc(long capacityBloc) {
         return blocRepository.findByCapaciteBloc(capacityBloc);
     }

     @Override
     public List<Bloc> findByCapaciteBlocAndNomBloc(long capacityBloc, String nomBloc) {
         return blocRepository.findByCapaciteBlocAndNomBloc(capacityBloc,nomBloc);
     }

     @Override
     public List<Bloc> findByCapaciteBlocGreaterThan(long capacityBloc) {
         return blocRepository.findByCapaciteBlocGreaterThan(capacityBloc);
     }

     @Override
     public List<Bloc> findByNomBlocContaining(String sousChaine) {
         return blocRepository.findByNomBlocContaining(sousChaine);
     }

     @Override
     public List<Bloc> findAllByOrderByNomBlocAsc() {
         return blocRepository.findAllByOrderByNomBlocAsc();
     }

     @Override
     public List<Bloc> findByNomBlocOrCapaciteBloc(String nomBloc, long capacityBloc) {
         return blocRepository.findByNomBlocOrCapaciteBloc(nomBloc,capacityBloc);
     }

     @Override
     public List<Bloc> findBlocByFoyer(Foyer foyer) {
         return null;
     }

     @Override
     public List<Bloc> findBlocByFoyerUniversiteAndFoyer(Universite universite, Foyer foyer) {
         return null;
     }

    @Override
    public Bloc affecterChambresABloc(List<Integer> numChambre, String nomBloc) {
        Bloc bloc = blocRepository.findByNomBloc(nomBloc);
        Set<Chambre> chambers = new HashSet<>();
        numChambre.forEach(numero -> {
            List<Chambre> chambreList = chambreRepository.selectByNum(numero);
            if (!chambreList.isEmpty()) {
                Chambre c = chambreList.get(0);
                c.setBloc(bloc);
                chambreRepository.save(c);
                chambers.add(c);
            }
        });
        bloc.setChambre(chambers);
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc affecterBlocAFoyer(String nomBloc, String nomFoyer) {
        return null;
    }


}
