package com.itesm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class NivelGeograficoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond NivelGeografico.list(params), model:[nivelGeograficoCount: NivelGeografico.count()]
    }

    def show(NivelGeografico nivelGeografico) {
        respond nivelGeografico
    }

    def create() {
        respond new NivelGeografico(params)
    }

    @Transactional
    def save(NivelGeografico nivelGeografico) {
        if (nivelGeografico == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (nivelGeografico.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond nivelGeografico.errors, view:'create'
            return
        }

        nivelGeografico.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nivelGeografico.label', default: 'NivelGeografico'), nivelGeografico.id])
                redirect nivelGeografico
            }
            '*' { respond nivelGeografico, [status: CREATED] }
        }
    }

    def edit(NivelGeografico nivelGeografico) {
        respond nivelGeografico
    }

    @Transactional
    def update(NivelGeografico nivelGeografico) {
        if (nivelGeografico == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (nivelGeografico.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond nivelGeografico.errors, view:'edit'
            return
        }

        nivelGeografico.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nivelGeografico.label', default: 'NivelGeografico'), nivelGeografico.id])
                redirect nivelGeografico
            }
            '*'{ respond nivelGeografico, [status: OK] }
        }
    }

    @Transactional
    def delete(NivelGeografico nivelGeografico) {

        if (nivelGeografico == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        nivelGeografico.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nivelGeografico.label', default: 'NivelGeografico'), nivelGeografico.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nivelGeografico.label', default: 'NivelGeografico'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
