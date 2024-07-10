job('ejemplo2-job-DSL') {
	description('Job DSL de ejemplo para el curso de Jenkins')
  	scm {
      		git('https://github.com/msanmillan/jenkins.job.parametrizado.git', 'main') { node ->
        		node / gitConfigName('msanmillan')
        		node / gitConfigEmail('jsanmillanunzaga@gmail.com')
      		}
    	} 
  	parameters {
   		stringParam('nombre', defaultValue = 'Mario', description = 'Parametro de cadena para el Job Booleano')
      		choiceParam('ciudad', ['Madrid', 'Sevilla', 'Barcelona', 'Valladolid'])
      		booleanParam('agente', false)
    	}
  	triggers {
    		cron('H/7 * * * *')
    	}
  	steps {
    		shell("bash jobscript.sh")
    	}
  	publishers {
      		mailer('jsanmillanunzaga@gmail.com', true, true)
      		slackNotifier {
		  notifyAborted(true)
		  notifyEveryFailure(true)
		  notifyNotBuilt(false)
		  notifyUnstable(false)
		  notifyBackToNormal(true)
		  notifySuccess(false)
		  notifyRepeatedFailure(false)
		  startNotification(false)
		  includeTestSummary(false)
		  includeCustomMessage(false)
		  customMessage(null)
		  sendAs(null)
		  commitInfoChoice('NONE')
		  teamDomain(null)
		  authToken(null)
        	}
    	}
}
