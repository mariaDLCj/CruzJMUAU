<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar</title>
        <c:import url="/INC/meta.inc"/>
        <link rel="stylesheet" type="text/css" href="${applicationScope.bootstrap}"/>
        <link rel="stylesheet" type="text/css" href="${applicationScope.estilo}"/>
    </head>

    <body>
        <!-- Cabecera con el menú de navegación  -->
        <c:import url="/INC/cabecera.jsp"/>
        <div class="container mt-5">
            <h2 class="text-center mb-4">Profesor a Eliminar</h2>
            <form method="post" action="${contexto}/Delete">
                <div class="mb-3">
                    <label class="form-label">*ID</label>
                    <input type="number" class="form-control" id="id" name="id" value="${sessionScope.profesorEliminar.id}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">*Nombre</label>
                    <input type="text" name="nombre" value="${sessionScope.profesorEliminar.nombre}" id="nombre" class="form-control" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">*Primer apellido</label>
                    <input type="text" name="apellido1" value="${sessionScope.profesorEliminar.apellido1}" id="apellido1" class="form-control" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Segundo apellido</label>
                    <input type="text" name="apellido2" value="${sessionScope.profesorEliminar.apellido2}" id="apellido2" class="form-control" readonly>
                </div>


                <div class="mb-3">
                    <label class="form-label">Calle</label>
                    <input type="text" class="form-control" id="calle" name="calle" placeholder="Ingresa tu calle" value="${sessionScope.profesorEliminar.direccion.calle}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">Número</label>
                    <!-- El número lo dejo como text porque hay casa que son 24A por ejemplo, que haya dos 24 -->
                    <input type="number" class="form-control" id="numero" name="numero" placeholder="Ingresa tu número" value="${sessionScope.profesorEliminar.direccion.numero}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">Localidad</label>
                    <input type="text" class="form-control" id="localidad" name="localidad" placeholder="Ingresa tu localidad" value="${sessionScope.profesorEliminar.direccion.localidad}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">Provincia</label>
                    <input type="text" class="form-control" id="provincia" name="provincia" placeholder="Ingresa tu provincia" value="${sessionScope.profesorEliminar.direccion.provincia}" readonly>
                </div>

                <div class="d-flex justify-content-center gap-2">
                    <input type="submit" class="btn btn-highlight w-100" name="confirmarElminiar" value="Confirmar" />
                    <input type="submit" class="btn btn-highlight w-100" value="Cancelar" name="cancelar" />
                </div>

            </form>
        </div>
    </body>
</html>
