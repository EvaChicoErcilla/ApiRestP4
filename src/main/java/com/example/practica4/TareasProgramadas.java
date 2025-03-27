package com.example.practica4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TareasProgramadas {

    private final ControladorRest controladorRest;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public TareasProgramadas(ControladorRest controladorRest) {
        this.controladorRest = controladorRest;
    }

    @Scheduled(fixedRate = 20 * 60 * 1000) // cada 20 minutos
    public void borrarPersonas() {
        controladorRest.borrarTodas();
        logger.info("🔄 Lista de personas borrada automáticamente cada 20 minutos.");
    }
}
