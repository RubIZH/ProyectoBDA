package com.itesm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PosicionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Posicion.list(params), model:[posicionCount: Posicion.count()]
    }

    def show(Posicion posicion) {
        respond posicion
    }

    def create() {
        respond new Posicion(params)
    }

    @Transactional
    def save(Posicion posicion) {
        if (posicion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (posicion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond posicion.errors, view:'create'
            return
        }

        posicion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'posicion.label', default: 'Posicion'), posicion.id])
                redirect posicion
            }
            '*' { respond posicion, [status: CREATED] }
        }
    }

    def edit(Posicion posicion) {
        respond posicion
    }

    @Transactional
    def update(Posicion posicion) {
        if (posicion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (posicion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond posicion.errors, view:'edit'
            return
        }

        posicion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'posicion.label', default: 'Posicion'), posicion.id])
                redirect posicion
            }
            '*'{ respond posicion, [status: OK] }
        }
    }

    @Transactional
    def delete(Posicion posicion) {

        if (posicion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        posicion.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'posicion.label', default: 'Posicion'), posicion.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'posicion.label', default: 'Posicion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
