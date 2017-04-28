package com.itesm

class Municipio {

  String nombreMunicipio
  Estado estado

  static mapping = {
  id(generator: 'org.hibernate.id.enhanced.SequenceStyleGenerator',
  params: [sequence_name: 'start_seq', initial_value: 100])
  }

  static constraints = {
    nombreMunicipio nullable: false

  }

  String toString() {return nombreMunicipio}
}
