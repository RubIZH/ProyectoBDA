package com.itesm

class Estado {


  String nombreEstado


  static mapping = {
  id(generator: 'org.hibernate.id.enhanced.SequenceStyleGenerator',
  params: [sequence_name: 'start_seq', initial_value: 100])
  }

  static constraints = {
    nombreEstado nullable: false

  }

  String toString() {return nombreEstado}
}
