package com.itesm

class Posicion {

    String nombrePosicion
    NivelGeografico nivelGeografico

    static mapping = {
    id(generator: 'org.hibernate.id.enhanced.SequenceStyleGenerator',
    params: [sequence_name: 'start_seq', initial_value: 100])
    }

    static constraints = {
      nombrePosicion nullable: false

    }

    String toString() {return nombrePosicion}
}
