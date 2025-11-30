package unicam.filieraAgricola.services;

import unicam.filieraAgricola.models.Evento;

public interface ObserverService {

    void notificaNuovoEvento(Evento evento);
}
