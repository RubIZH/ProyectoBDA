package com.itesm

class Partido {

    String nombrePartido

    static mapping = {
      id(generator: 'org.hibernate.id.enhanced.SequenceStyleGenerator',
      params: [sequence_name: 'start_seq', initial_value: 100])
    }

    static constraints = {

      nombrePartido nullable: false

    }
}
