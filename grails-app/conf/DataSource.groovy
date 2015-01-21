dataSource {
    pooled = true
    dbCreate = "update"
    pooling = true
    logSql = false

    driverClassName = "org.postgresql.Driver"
    dialect = "org.hibernate.dialect.PostgreSQLDialect"
    url = "jdbc:postgresql://127.0.0.1:5432/inventory"
    username = 'ingo'
    password = 'ingo'

    autoReconnect = true
    properties {
        initialSize = 10
        maxActive = 500
        minEvictableIdleTimeMillis = 300000
        timeBetweenEvictionRunsMillis = 300000
        numTestsPerEvictionRun = 5
        testOnBorrow = true
        testWhileIdle = true
        testOnReturn = true
        validationQuery = "SELECT 1"
    }
}

hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
        }
    }
    test {
        dataSource {
            dbCreate = "update"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
        }
    }
}
