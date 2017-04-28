package com.itesm

class Candidato {

  String nombre
  String apellidoPaterno
  String apellidoMaterno
  Date fechaDeNacimiento
  String direccion
  String sexo
  String educacion
  String etnia
  String religion
  Posicion posicion
  Partido partido
  Estado estado
  Municipio municipio




  static mapping = {
  id(generator: 'org.hibernate.id.enhanced.SequenceStyleGenerator',
  params: [sequence_name: 'start_seq', initial_value: 100])
  }

  static constraints = {
    sexo(inList: ["Femenino", "Masculino"])
    etnia(inList: ["Hispano", "Caucásico","Africano","Asiático","Mixto"])
    educacion(inList: ["Primaria", "Secundaria","Preparatoria","Licenciatura","Carrera Técnica","Pos"])
    religion(inList: ["Católica", "Cristiana","Judía","Musulmana","Budista","Taoista","Otro"])
  }

}
