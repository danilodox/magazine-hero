package com.br.magazinehero.data.model

class Data (
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Comic>
        )