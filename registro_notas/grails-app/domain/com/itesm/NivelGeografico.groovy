package com.itesm

class NivelGeografico {

    String nombreNivelGeografico


    static mapping = {
    id(generator: 'org.hibernate.id.enhanced.SequenceStyleGenerator',
    params: [sequence_name: 'start_seq', initial_value: 100])
  }

  static constraints = {

    nombreNivelGeografico nullable: false

    }

    String toString() {return nombreNivelGeografico}
}
