
def artifacts = [
	'HSMtDealingTermTransactionCore',
	'HSMtExchangeRateModule',
	'HSMtFlexWebServer',
	'HSMtFrontEndIntegrationModule',
	'HSMtFundPortfolioModule',
	'HSMtIntegrationCore',
	'HSMtLiquidityModule',
	'HSMtMasterDataHSMOModule',
	'HSMtMasterDataHSPADModule',
	'HSMtPerformanceDecompositionModule',
	'HSMtPriceModule',
	'HSMtSPMCoreModule',
	'HSMtSPMMasterDataModule',
	'HSMtSPMPricingModule'
]
	
def versionRange = 6000..<7000

artifacts.each { artifact ->
	versionRange.each { version ->
		def url = "http://artifactory:8081/artifactory/snapshot-local/ch.hedgesphere/${artifact}/5.10.0-${version}".toURL()
		def conn = url.openConnection()
		conn.doOutput = true
		conn.requestMethod = 'DELETE'
		conn.connect()
		println  conn.responseCode
	}
}
	