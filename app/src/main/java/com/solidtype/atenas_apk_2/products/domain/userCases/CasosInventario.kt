package com.solidtype.atenas_apk_2.products.domain.userCases

data class CasosInventario(
    val getProductos: getProductos,
    val createProductos: createProductos,
    val getProductosByCodigo: getProductosByCodigo,
    val searchProductos: SearchProductosLike,
    val updateProducto: UpdateProducto,
    val deleteProductos: DeleteProductos
)
