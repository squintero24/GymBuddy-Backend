package com.example.gymbuddy.service.impl;

import com.example.gymbuddy.dto.PersonaDto;
import com.example.gymbuddy.mapper.IPersonaMapper;
import com.example.gymbuddy.model.UserPlans;
import com.example.gymbuddy.model.UserRoles;
import com.example.gymbuddy.model.Users;
import com.example.gymbuddy.repository.*;
import com.example.gymbuddy.service.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IPlanRepository planRepository;

    @Autowired
    private IUserPlanRepository userPlanRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRolesRepository rolesRepository;

    @Autowired
    private IUserRoleRepository userRoleRepository;


    @Override
    public List<PersonaDto> findAllPersonas() {

        var personasBD = this.personRepository.findAll();

        return personasBD.stream()
                .map(IPersonaMapper.INSTANCE::toPersonaDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PersonaDto createPerson(PersonaDto personaDto) throws Exception {

        var personaEntity = IPersonaMapper.INSTANCE.toPersonaEntity(personaDto);
        var planEntity = this.planRepository.findById(personaDto.getIdPlan()).orElseThrow(Exception::new);
        var rolEntity = this.rolesRepository.findById(personaDto.getIdRol()).orElseThrow(Exception::new);


        var personaCreated = this.personRepository.save(personaEntity);


        var userToCreate = new Users();

        userToCreate.setIdPerson(personaCreated.getId());
        userToCreate.setCreationDate(new Date());
        userToCreate.setUserName(this.generateUserCode());
        userToCreate.setPassword(this.generateUserPasswd());
        userToCreate.setUserValido(0L);

        var userCreated = this.userRepository.save(userToCreate);

        var userRolesToCreate = new UserRoles();

        userRolesToCreate.setIdRol(rolEntity.getId());
        userRolesToCreate.setIdUser(userCreated.getId());
        userRolesToCreate.setCreationDate(new Date());
        this.userRoleRepository.save(userRolesToCreate);


        var userPlanToCreate = new UserPlans();

        userPlanToCreate.setIdPlan(planEntity.getId());
        userPlanToCreate.setIdUser(userCreated.getId());
        userPlanToCreate.setCreationDate(new Date());
        userPlanToCreate.setStartDate(personaDto.getFechaDesdePlan());
        userPlanToCreate.setEndDate(personaDto.getFechaHastaPlan());

        this.userPlanRepository.save(userPlanToCreate);


        return IPersonaMapper.INSTANCE.toPersonaDto(personaCreated);
    }

    public String generateUserPasswd() {

        int longitudMaxima = 6;

        // Caracteres válidos para la contraseña
        String caracteresValidos = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        // Genera una contraseña aleatoria
        StringBuilder contrasena = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < longitudMaxima; i++) {
            int indice = rand.nextInt(caracteresValidos.length());
            char caracter = caracteresValidos.charAt(indice);
            contrasena.append(caracter);
        }

        return contrasena.toString();
    }

    public String generateUserCode() {

        var isRepeated = true;
        var userCode = "";

        while (isRepeated) {
            int year = Year.now().getValue();

            // Obten los dígitos necesarios del año (primer, tercer y cuarto dígitos)
            int primerDigito = year % 10;
            int tercerDigito = (year / 10) % 10;
            int cuartoDigito = (year / 100) % 10;

            // Genera los siguientes 4 números aleatorios
            var rand = new Random();
            int siguienteCuatroDigitos = rand.nextInt(10000);

            // Formatea el código de usuario para asegurarte de que tenga 7 cifras
            var formato = new DecimalFormat("0000");

            userCode = primerDigito + tercerDigito + cuartoDigito + formato.format(siguienteCuatroDigitos);

            if (this.userRepository.countByUserName(userCode)==0) {
                isRepeated = false;
            }
        }

        // Combina los dígitos del año y los números aleatorios en un solo string
        return userCode;

    }

    @Override
    public PersonaDto updatePerson(PersonaDto personaDto) throws Exception {

        var personaBD = this.personRepository.findById(personaDto.getId()).orElseThrow(Exception::new);

        personaBD.setAddress(personaDto.getAddress());
        personaBD.setEmail(personaDto.getEmail());
        personaBD.setName(personaDto.getName());
        personaBD.setLastName(personaDto.getLastName());
        personaBD.setBirthDate(personaDto.getBirthDate());
        personaBD.setIdTypeDocument(personaDto.getIdTipoDocumento());
        personaBD.setNumDocument(personaDto.getNumDocument());
        personaBD.setNeighborhood(personaDto.getNeighborhood());
        personaBD.setPhoto(personaDto.getPhoto());
        personaBD.setPhoneNumber(personaDto.getPhoneNumber());
        personaBD.setAddress(personaDto.getAddress());

        var personaUpdated = this.personRepository.save(personaBD);


        return IPersonaMapper.INSTANCE.toPersonaDto(personaUpdated);
    }

    @Override
    public Boolean deletePerson(Long idPersona) {
        var personaToDelete = this.personRepository.findById(idPersona);

        if (personaToDelete.isPresent()) {
            this.personRepository.deleteById(idPersona);
            return true;
        } else {
            return false;
        }
    }
}
