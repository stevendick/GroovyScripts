import groovy.json.JsonSlurper

def artifactoryUrl = 'http://artifactory:8081/artifactory'
def repo = 'snapshot-local'
def pkg = 'ch.hedgesphere'

def getProjects = {
    def jsonPayload = "${artifactoryUrl}/api/storage/${repo}/${pkg}".toURL().text

    def slurper = new JsonSlurper()
    def projects = slurper.parseText(jsonPayload)

    def artifacts = []
    artifacts.addAll(projects.children.uri.findAll { uri ->
      uri.startsWith '/HSMt'
    })
    artifacts
}

def artifacts = getProjects()

println artifacts

def versionRange = 6578..<8505

artifacts.each { artifact ->
    versionRange.each { version ->
        def url = "${artifactoryUrl}/${repo}/${pkg}${artifact}/5.1.0-${version}".toURL()
        def conn = url.openConnection()
        conn.doOutput = true
        conn.requestMethod = 'DELETE'
        conn.connect()
        println conn.responseCode
    }
}
