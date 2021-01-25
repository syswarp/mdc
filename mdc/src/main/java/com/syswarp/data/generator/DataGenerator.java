package com.syswarp.data.generator;

import com.vaadin.flow.spring.annotation.SpringComponent;

import com.syswarp.data.service.AgenciasRepository;
import com.syswarp.data.entity.Agencias;
import com.syswarp.data.service.BuquesRepository;
import com.syswarp.data.entity.Buques;
import com.syswarp.data.service.EmpresasRepository;
import com.syswarp.data.entity.Empresas;
import com.syswarp.data.service.EstadosRepository;
import com.syswarp.data.entity.Estados;
import com.syswarp.data.service.LanchasRepository;
import com.syswarp.data.entity.Lanchas;
import com.syswarp.data.service.ManiobrasRepository;
import com.syswarp.data.entity.Maniobras;
import com.syswarp.data.service.MuellesRepository;
import com.syswarp.data.entity.Muelles;
import com.syswarp.data.service.PracticosRepository;
import com.syswarp.data.entity.Practicos;
import com.syswarp.data.service.PuertosRepository;
import com.syswarp.data.entity.Puertos;
import com.syswarp.data.service.RemisesRepository;
import com.syswarp.data.entity.Remises;
import com.syswarp.data.service.TimeshipRepository;
import com.syswarp.data.entity.Timeship;
import com.syswarp.data.service.TipousuariosRepository;
import com.syswarp.data.entity.Tipousuarios;
import com.syswarp.data.service.UsuariosRepository;
import com.syswarp.data.entity.Usuarios;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.exampledata.DataType;
import org.vaadin.artur.exampledata.ExampleDataGenerator;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(AgenciasRepository agenciasRepository, BuquesRepository buquesRepository,
            EmpresasRepository empresasRepository, EstadosRepository estadosRepository,
            LanchasRepository lanchasRepository, ManiobrasRepository maniobrasRepository,
            MuellesRepository muellesRepository, PracticosRepository practicosRepository,
            PuertosRepository puertosRepository, RemisesRepository remisesRepository,
            TimeshipRepository timeshipRepository, TipousuariosRepository tipousuariosRepository,
            UsuariosRepository usuariosRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (agenciasRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");

            logger.info("... generating 100 Agencias entities...");
            ExampleDataGenerator<Agencias> agenciasRepositoryGenerator = new ExampleDataGenerator<>(Agencias.class,
                    LocalDateTime.of(2021, 1, 25, 0, 0, 0));
            agenciasRepositoryGenerator.setData(Agencias::setId, DataType.ID);
            agenciasRepositoryGenerator.setData(Agencias::setId, DataType.NUMBER_UP_TO_100);
            agenciasRepositoryGenerator.setData(Agencias::setAlias, DataType.WORD);
            agenciasRepositoryGenerator.setData(Agencias::setCuit, DataType.WORD);
            agenciasRepositoryGenerator.setData(Agencias::setDomicilio, DataType.WORD);
            agenciasRepository.saveAll(agenciasRepositoryGenerator.create(100, seed));

            logger.info("... generating 100 Buques entities...");
            ExampleDataGenerator<Buques> buquesRepositoryGenerator = new ExampleDataGenerator<>(Buques.class,
                    LocalDateTime.of(2021, 1, 25, 0, 0, 0));
            buquesRepositoryGenerator.setData(Buques::setId, DataType.ID);
            buquesRepositoryGenerator.setData(Buques::setId, DataType.NUMBER_UP_TO_100);
            buquesRepositoryGenerator.setData(Buques::setImo, DataType.WORD);
            buquesRepositoryGenerator.setData(Buques::setFlag, DataType.WORD);
            buquesRepositoryGenerator.setData(Buques::setGrt, DataType.NUMBER_UP_TO_100);
            buquesRepositoryGenerator.setData(Buques::setNombre, DataType.WORD);
            buquesRepositoryGenerator.setData(Buques::setEslora, DataType.NUMBER_UP_TO_100);
            buquesRepositoryGenerator.setData(Buques::setManga, DataType.NUMBER_UP_TO_100);
            buquesRepositoryGenerator.setData(Buques::setPuntal, DataType.NUMBER_UP_TO_100);
            buquesRepositoryGenerator.setData(Buques::setCoef, DataType.NUMBER_UP_TO_100);
            buquesRepositoryGenerator.setData(Buques::setCoeffac, DataType.NUMBER_UP_TO_100);
            buquesRepositoryGenerator.setData(Buques::setGrtfac, DataType.NUMBER_UP_TO_100);
            buquesRepository.saveAll(buquesRepositoryGenerator.create(100, seed));

            logger.info("... generating 100 Empresas entities...");
            ExampleDataGenerator<Empresas> empresasRepositoryGenerator = new ExampleDataGenerator<>(Empresas.class,
                    LocalDateTime.of(2021, 1, 25, 0, 0, 0));
            empresasRepositoryGenerator.setData(Empresas::setId, DataType.ID);
            empresasRepositoryGenerator.setData(Empresas::setId, DataType.NUMBER_UP_TO_100);
            empresasRepositoryGenerator.setData(Empresas::setEmpresa, DataType.WORD);
            empresasRepositoryGenerator.setData(Empresas::setCuit, DataType.WORD);
            empresasRepositoryGenerator.setData(Empresas::setDomicilio, DataType.WORD);
            empresasRepository.saveAll(empresasRepositoryGenerator.create(100, seed));

            logger.info("... generating 100 Estados entities...");
            ExampleDataGenerator<Estados> estadosRepositoryGenerator = new ExampleDataGenerator<>(Estados.class,
                    LocalDateTime.of(2021, 1, 25, 0, 0, 0));
            estadosRepositoryGenerator.setData(Estados::setId, DataType.ID);
            estadosRepositoryGenerator.setData(Estados::setId, DataType.NUMBER_UP_TO_100);
            estadosRepositoryGenerator.setData(Estados::setEstado, DataType.WORD);
            estadosRepository.saveAll(estadosRepositoryGenerator.create(100, seed));

            logger.info("... generating 100 Lanchas entities...");
            ExampleDataGenerator<Lanchas> lanchasRepositoryGenerator = new ExampleDataGenerator<>(Lanchas.class,
                    LocalDateTime.of(2021, 1, 25, 0, 0, 0));
            lanchasRepositoryGenerator.setData(Lanchas::setId, DataType.ID);
            lanchasRepositoryGenerator.setData(Lanchas::setId, DataType.NUMBER_UP_TO_100);
            lanchasRepositoryGenerator.setData(Lanchas::setLancha, DataType.WORD);
            lanchasRepositoryGenerator.setData(Lanchas::setCuit, DataType.WORD);
            lanchasRepositoryGenerator.setData(Lanchas::setDomicilio, DataType.WORD);
            lanchasRepository.saveAll(lanchasRepositoryGenerator.create(100, seed));

            logger.info("... generating 100 Maniobras entities...");
            ExampleDataGenerator<Maniobras> maniobrasRepositoryGenerator = new ExampleDataGenerator<>(Maniobras.class,
                    LocalDateTime.of(2021, 1, 25, 0, 0, 0));
            maniobrasRepositoryGenerator.setData(Maniobras::setId, DataType.ID);
            maniobrasRepositoryGenerator.setData(Maniobras::setId, DataType.NUMBER_UP_TO_100);
            maniobrasRepositoryGenerator.setData(Maniobras::setManiobra, DataType.WORD);
            maniobrasRepository.saveAll(maniobrasRepositoryGenerator.create(100, seed));

            logger.info("... generating 100 Muelles entities...");
            ExampleDataGenerator<Muelles> muellesRepositoryGenerator = new ExampleDataGenerator<>(Muelles.class,
                    LocalDateTime.of(2021, 1, 25, 0, 0, 0));
            muellesRepositoryGenerator.setData(Muelles::setId, DataType.ID);
            muellesRepositoryGenerator.setData(Muelles::setId, DataType.NUMBER_UP_TO_100);
            muellesRepositoryGenerator.setData(Muelles::setMuelle, DataType.NUMBER_UP_TO_100);
            muellesRepositoryGenerator.setData(Muelles::setIdpuerto, DataType.NUMBER_UP_TO_100);
            muellesRepositoryGenerator.setData(Muelles::setKilometro, DataType.NUMBER_UP_TO_100);
            muellesRepositoryGenerator.setData(Muelles::setEmax, DataType.WORD);
            muellesRepositoryGenerator.setData(Muelles::setRv, DataType.NUMBER_UP_TO_100);
            muellesRepositoryGenerator.setData(Muelles::setEstructura, DataType.WORD);
            muellesRepositoryGenerator.setData(Muelles::setDet, DataType.NUMBER_UP_TO_100);
            muellesRepositoryGenerator.setData(Muelles::setLatitud, DataType.NUMBER_UP_TO_100);
            muellesRepositoryGenerator.setData(Muelles::setLongitud, DataType.NUMBER_UP_TO_100);
            muellesRepository.saveAll(muellesRepositoryGenerator.create(100, seed));

            logger.info("... generating 100 Practicos entities...");
            ExampleDataGenerator<Practicos> practicosRepositoryGenerator = new ExampleDataGenerator<>(Practicos.class,
                    LocalDateTime.of(2021, 1, 25, 0, 0, 0));
            practicosRepositoryGenerator.setData(Practicos::setId, DataType.ID);
            practicosRepositoryGenerator.setData(Practicos::setId, DataType.NUMBER_UP_TO_100);
            practicosRepositoryGenerator.setData(Practicos::setPractico, DataType.WORD);
            practicosRepositoryGenerator.setData(Practicos::setHabilitacion, DataType.WORD);
            practicosRepositoryGenerator.setData(Practicos::setTelefono, DataType.WORD);
            practicosRepositoryGenerator.setData(Practicos::setCelular, DataType.WORD);
            practicosRepositoryGenerator.setData(Practicos::setOtro_telefono, DataType.WORD);
            practicosRepositoryGenerator.setData(Practicos::setDireccion, DataType.WORD);
            practicosRepositoryGenerator.setData(Practicos::setF_revisacion_medica, DataType.DATE_OF_BIRTH);
            practicosRepositoryGenerator.setData(Practicos::setF_vencimiento_chaleco, DataType.DATE_OF_BIRTH);
            practicosRepositoryGenerator.setData(Practicos::setIdempresa, DataType.NUMBER_UP_TO_100);
            practicosRepository.saveAll(practicosRepositoryGenerator.create(100, seed));

            logger.info("... generating 100 Puertos entities...");
            ExampleDataGenerator<Puertos> puertosRepositoryGenerator = new ExampleDataGenerator<>(Puertos.class,
                    LocalDateTime.of(2021, 1, 25, 0, 0, 0));
            puertosRepositoryGenerator.setData(Puertos::setId, DataType.ID);
            puertosRepositoryGenerator.setData(Puertos::setId, DataType.NUMBER_UP_TO_100);
            puertosRepositoryGenerator.setData(Puertos::setPuerto, DataType.WORD);
            puertosRepositoryGenerator.setData(Puertos::setAdicional, DataType.NUMBER_UP_TO_100);
            puertosRepositoryGenerator.setData(Puertos::setBonificacion, DataType.NUMBER_UP_TO_100);
            puertosRepositoryGenerator.setData(Puertos::setInterno, DataType.NUMBER_UP_TO_100);
            puertosRepositoryGenerator.setData(Puertos::setLatitud, DataType.NUMBER_UP_TO_100);
            puertosRepositoryGenerator.setData(Puertos::setLongitud, DataType.NUMBER_UP_TO_100);
            puertosRepositoryGenerator.setData(Puertos::setDomicilio, DataType.WORD);
            puertosRepository.saveAll(puertosRepositoryGenerator.create(100, seed));

            logger.info("... generating 100 Remises entities...");
            ExampleDataGenerator<Remises> remisesRepositoryGenerator = new ExampleDataGenerator<>(Remises.class,
                    LocalDateTime.of(2021, 1, 25, 0, 0, 0));
            remisesRepositoryGenerator.setData(Remises::setId, DataType.ID);
            remisesRepositoryGenerator.setData(Remises::setId, DataType.NUMBER_UP_TO_100);
            remisesRepositoryGenerator.setData(Remises::setRemis, DataType.WORD);
            remisesRepositoryGenerator.setData(Remises::setCuit, DataType.WORD);
            remisesRepositoryGenerator.setData(Remises::setDomicilio, DataType.WORD);
            remisesRepository.saveAll(remisesRepositoryGenerator.create(100, seed));

            logger.info("... generating 100 Timeship entities...");
            ExampleDataGenerator<Timeship> timeshipRepositoryGenerator = new ExampleDataGenerator<>(Timeship.class,
                    LocalDateTime.of(2021, 1, 25, 0, 0, 0));
            timeshipRepositoryGenerator.setData(Timeship::setId, DataType.ID);
            timeshipRepositoryGenerator.setData(Timeship::setId, DataType.NUMBER_UP_TO_100);
            timeshipRepositoryGenerator.setData(Timeship::setIdbuque, DataType.NUMBER_UP_TO_100);
            timeshipRepositoryGenerator.setData(Timeship::setIdagencia, DataType.NUMBER_UP_TO_100);
            timeshipRepositoryGenerator.setData(Timeship::setFecha_etp, DataType.DATE_OF_BIRTH);
            timeshipRepositoryGenerator.setData(Timeship::setIdempresa, DataType.NUMBER_UP_TO_100);
            timeshipRepositoryGenerator.setData(Timeship::setObservaciones, DataType.WORD);
            timeshipRepositoryGenerator.setData(Timeship::setIdpuertodesde, DataType.NUMBER_UP_TO_100);
            timeshipRepositoryGenerator.setData(Timeship::setIdmuelledesde, DataType.NUMBER_UP_TO_100);
            timeshipRepositoryGenerator.setData(Timeship::setIdpuertohasta, DataType.NUMBER_UP_TO_100);
            timeshipRepositoryGenerator.setData(Timeship::setIdmuellehasta, DataType.NUMBER_UP_TO_100);
            timeshipRepositoryGenerator.setData(Timeship::setFecha_transaccion, DataType.DATE_OF_BIRTH);
            timeshipRepositoryGenerator.setData(Timeship::setIdmaniobra, DataType.NUMBER_UP_TO_100);
            timeshipRepositoryGenerator.setData(Timeship::setIdestado, DataType.NUMBER_UP_TO_100);
            timeshipRepositoryGenerator.setData(Timeship::setF_est_inicio_maniobra, DataType.DATE_OF_BIRTH);
            timeshipRepositoryGenerator.setData(Timeship::setF_est_fin_maniobra, DataType.DATE_OF_BIRTH);
            timeshipRepositoryGenerator.setData(Timeship::setIdpractico, DataType.NUMBER_UP_TO_100);
            timeshipRepositoryGenerator.setData(Timeship::setF_asignacion, DataType.DATE_OF_BIRTH);
            timeshipRepositoryGenerator.setData(Timeship::setIdlancha, DataType.NUMBER_UP_TO_100);
            timeshipRepositoryGenerator.setData(Timeship::setIdremis, DataType.NUMBER_UP_TO_100);
            timeshipRepositoryGenerator.setData(Timeship::setNrodespacho, DataType.NUMBER_UP_TO_100);
            timeshipRepositoryGenerator.setData(Timeship::setF_fin_maniobra, DataType.DATE_OF_BIRTH);
            timeshipRepositoryGenerator.setData(Timeship::setF_desembarco, DataType.DATE_OF_BIRTH);
            timeshipRepositoryGenerator.setData(Timeship::setF_presentacion, DataType.DATE_OF_BIRTH);
            timeshipRepository.saveAll(timeshipRepositoryGenerator.create(100, seed));

            logger.info("... generating 100 Tipousuarios entities...");
            ExampleDataGenerator<Tipousuarios> tipousuariosRepositoryGenerator = new ExampleDataGenerator<>(
                    Tipousuarios.class, LocalDateTime.of(2021, 1, 25, 0, 0, 0));
            tipousuariosRepositoryGenerator.setData(Tipousuarios::setId, DataType.ID);
            tipousuariosRepositoryGenerator.setData(Tipousuarios::setId, DataType.NUMBER_UP_TO_100);
            tipousuariosRepositoryGenerator.setData(Tipousuarios::setTipousuario, DataType.WORD);
            tipousuariosRepository.saveAll(tipousuariosRepositoryGenerator.create(100, seed));

            logger.info("... generating 100 Usuarios entities...");
            ExampleDataGenerator<Usuarios> usuariosRepositoryGenerator = new ExampleDataGenerator<>(Usuarios.class,
                    LocalDateTime.of(2021, 1, 25, 0, 0, 0));
            usuariosRepositoryGenerator.setData(Usuarios::setId, DataType.ID);
            usuariosRepositoryGenerator.setData(Usuarios::setId, DataType.NUMBER_UP_TO_100);
            usuariosRepositoryGenerator.setData(Usuarios::setEmail, DataType.EMAIL);
            usuariosRepositoryGenerator.setData(Usuarios::setClave, DataType.WORD);
            usuariosRepositoryGenerator.setData(Usuarios::setNombre, DataType.WORD);
            usuariosRepositoryGenerator.setData(Usuarios::setHabilitado, DataType.WORD);
            usuariosRepositoryGenerator.setData(Usuarios::setIdtipousuario, DataType.NUMBER_UP_TO_100);
            usuariosRepository.saveAll(usuariosRepositoryGenerator.create(100, seed));

            logger.info("Generated demo data");
        };
    }

}