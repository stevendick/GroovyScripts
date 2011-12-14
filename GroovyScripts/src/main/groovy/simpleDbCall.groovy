import groovy.sql.Sql

def hostname='localhost'
def port = '1433'
def dbName = 'hspad_demo_co'
def user ='sa'
def password = 'sa2005'
def driverName = 'net.sourceforge.jtds.jdbc.Driver'

def sql = Sql.newInstance("jdbc:jtds:sqlserver://${hostname}:${port}/${dbName}", user, password, driverName)
// if you have a datasource, you can do:
//def sql = new Sql(myDataSource)

sql.eachRow('select * from t_settingglobal') { row ->
  println "version [${row.SCHEMA_VERSION}] release [${row.SCHEMA_RELEASE}]"
}


