package edu.eam.ingesoft.logica.credito;


    public class EvaluacionCredito {
        private String nombreSolicitante;
        private double ingresosMensuales;
        private int numeroCreditosActivos;
        private int puntajeCredito;
        private double valorCreditoSolicitado;
        private boolean tieneCodedor;

        public EvaluacionCredito(String nombreSolicitante, double ingresosMensuales,
                                 int numeroCreditosActivos, int puntajeCredito,
                                 double valorCreditoSolicitado, boolean tieneCodedor) {
            this.nombreSolicitante = nombreSolicitante;
            this.ingresosMensuales = ingresosMensuales;
            this.numeroCreditosActivos = numeroCreditosActivos;
            this.puntajeCredito = puntajeCredito;
            this.valorCreditoSolicitado = valorCreditoSolicitado;
            this.tieneCodedor = tieneCodedor;
        }
    
    /**
     * Calcula la tasa de interés mensual a partir de la tasa nominal anual.
     * 
     * @param tasaNominalAnual Tasa nominal anual en porcentaje
     * @return Tasa mensual en porcentaje
     */
    public double calcularTasaMensual(double tasaNominalAnual) {
        return (tasaNominalAnual / 12.0);
    }
    
    /**
     * Calcula la cuota mensual del crédito usando la fórmula de amortización francesa.
     * Formula: Cuota = M * (im * (1+im)^n) / ((1+im)^n - 1)
     * 
     * @param tasaNominalAnual Tasa nominal anual en porcentaje
     * @param plazoMeses Plazo del crédito en meses
     * @return Valor de la cuota mensual en pesos
     */
    public double calcularCuotaMensual(double tasaNominalAnual, int plazoMeses) {
        double tasaMensual = calcularTasaMensual(tasaNominalAnual) / 100.0;
        double M = valorCreditoSolicitado;

        if (tasaMensual == 0) {
            return M / plazoMeses;
        }

        return (M * tasaMensual * Math.pow(1 + tasaMensual, plazoMeses)) /
                (Math.pow(1 + tasaMensual, plazoMeses) - 1);
    }
    
    /**
     * Evalúa si el crédito debe ser aprobado según las reglas de negocio:
     * - Perfil bajo (puntaje < 500): Rechazo automático
     * - Perfil medio (500 ≤ puntaje ≤ 700): Requiere codeudor y cuota ≤ 25% de ingresos
     * - Perfil alto (puntaje > 700 y < 2 créditos): Cuota ≤ 30% de ingresos
     * 
     * @param tasaNominalAnual Tasa nominal anual en porcentaje
     * @param plazoMeses Plazo del crédito en meses
     * @return true si el crédito es aprobado, false si es rechazado
     */
    public boolean evaluarAprobacion(double tasaNominalAnual, int plazoMeses) {
        if (puntajeCredito < 500) {
            return false;
        }

        double cuota = calcularCuotaMensual(tasaNominalAnual, plazoMeses);

        if (puntajeCredito >= 500 && puntajeCredito <= 700) {
            return tieneCodedor && (cuota <= ingresosMensuales * 0.25);
        }
        if (puntajeCredito > 700 && numeroCreditosActivos < 2) {
            // Perfil alto: cuota <= 30% ingresos
            return cuota <= ingresosMensuales * 0.30;
        }

        return false;
    }


        /**
         * Obtiene el nombre del solicitante.
         * @return Nombre completo del solicitante
         */
    public String getNombreSolicitante() {
        return nombreSolicitante;
    }
    
    /**
     * Establece el nombre del solicitante.
     * @param nombreSolicitante Nombre completo del solicitante
     */
    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }
    
    /**
     * Obtiene los ingresos mensuales del solicitante.
     * @return Ingresos mensuales en pesos
     */
    
    public double getIngresosMensuales() {
        return ingresosMensuales;
    }
    
    /**
     * Establece los ingresos mensuales del solicitante.
     * @param ingresosMensuales Ingresos mensuales en pesos
     */
    public void setIngresosMensuales(double ingresosMensuales) {
        this.ingresosMensuales = ingresosMensuales;
    }
    
    /**
     * Obtiene el número de créditos activos del solicitante.
     * @return Cantidad de créditos activos
     */
    public int getNumeroCreditosActivos() {
        return numeroCreditosActivos;
    }
    
    /**
     * Establece el número de créditos activos del solicitante.
     * @param numeroCreditosActivos Cantidad de créditos activos
     */
    public void setNumeroCreditosActivos(int numeroCreditosActivos) {
        this.numeroCreditosActivos = numeroCreditosActivos;
    }
    
    /**
     * Obtiene el puntaje de crédito del solicitante.
     * @return Puntaje crediticio (0-1000)
     */
    public int getPuntajeCredito() {
        return puntajeCredito;
    }
    
    /**
     * Establece el puntaje de crédito del solicitante.
     * @param puntajeCredito Puntaje crediticio (0-1000)
     */
    public void setPuntajeCredito(int puntajeCredito) {
        this.puntajeCredito = puntajeCredito;
    }
    
    /**
     * Obtiene el valor del crédito solicitado.
     * @return Monto del crédito en pesos
     */
    public double getValorCreditoSolicitado() {
        return valorCreditoSolicitado;
    }
    
    /**
     * Establece el valor del crédito solicitado.
     * @param valorCreditoSolicitado Monto del crédito en pesos
     */
    public void setValorCreditoSolicitado(double valorCreditoSolicitado) {
        this.valorCreditoSolicitado = valorCreditoSolicitado;
    }
    
    /**
     * Verifica si el solicitante tiene codeudor.
     * @return true si tiene codeudor, false en caso contrario
     */
    public boolean isTieneCodedor() {
        return tieneCodedor;
    }
    
    /**
     * Establece si el solicitante tiene codeudor.
     * @param tieneCodedor true si tiene codeudor, false en caso contrario
     */
    public void setTieneCodedor(boolean tieneCodedor) {
        this.tieneCodedor = tieneCodedor;
    }
}