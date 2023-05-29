package com.brodygaudel.gestionemployee.util.idgenerator;

import com.brodygaudel.gestionemployee.enums.Sex;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class IdGenerator {

    private final FcompterRepository fcompterRepository;
    private final McompterRepository mcompterRepository;

    public IdGenerator(FcompterRepository fcompterRepository, McompterRepository mcompterRepository) {
        this.fcompterRepository = fcompterRepository;
        this.mcompterRepository = mcompterRepository;
    }

    public String generateId(Sex sex){
        if(sex == Sex.M){
            return autoGenerateForMasculine();
        }
        return autoGenerateForFeminine();
    }

    private String autoGenerateForMasculine(){
        try {
            List<Mcompter> compters = mcompterRepository.findAll();
            Mcompter compter;
            if(compters.isEmpty()) {
                compter = new Mcompter((long) 1);
            }
            else {
                compter = compters.get(compters.size() - 1);
                mcompterRepository.deleteById(compter.getId());
            }
            return generateForMasculine(compter);
        }catch(Exception e) {
            log.error("Id not generated due to :"+e.getMessage());
            return null;
        }
    }

    private String autoGenerateForFeminine(){
        try {
            List<Fcompter> compters = fcompterRepository.findAll();
            Fcompter compter;
            if(compters.isEmpty()) {
                compter = new Fcompter((long) 1);
            }
            else {
                compter = compters.get(compters.size() - 1);
                fcompterRepository.deleteById(compter.getId());
            }
            return generateForFeminine(compter);
        }catch(Exception e) {
            log.error("Id not generated due to :"+e.getMessage());
            return null;
        }
    }


    private String generateForMasculine(Mcompter compter) {
        Long cpt = compter.getId()+1;
        mcompterRepository.save(new Mcompter(cpt));
        String prefix = String.valueOf(LocalDate.now().getYear());
        String counterStr = String.format("%04d", cpt);
        return prefix +"M"+counterStr;
    }

    private String generateForFeminine(Fcompter compter) {
        Long cpt = compter.getId()+1;
        fcompterRepository.save( new Fcompter(cpt));
        String prefix = String.valueOf(LocalDate.now().getYear());
        String counterStr = String.format("%04d", cpt);
        return prefix +"F"+ counterStr;
    }
}
