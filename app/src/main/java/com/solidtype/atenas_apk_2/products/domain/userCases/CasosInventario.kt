package com.solidtype.atenas_apk_2.products.domain.userCases

data class CasosInventario(
    val getProductos: getProductos,
    val createProductos: createProductos,
    val getProductosByCodigo: getProductosByCodigo,
    val searchProductos: SearchProductosLike,
    val updateProducto: UpdateProducto,
    val deleteProductos: DeleteProductos,
    val exportarExcel: ExportarExcel,
    val importarExcelFile: ImportarExcelFile,
    val syncProductos: SyncProductos,
    val agregarCategoria: CrearCategoria,
    val getCategorias: GetCategorias,
    val eliminarCategorias: EliminarCategorias,
    val buscarCategorias: BuscarCategorias
)
