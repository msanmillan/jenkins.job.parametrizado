job('ejemplo-dsl-job2') {
  description('Job DSL de ejemplo generado mediante un Seed Job')
  scm {
    git('https://github.com/msanmillan/jenkins.job.parametrizado.git', 'main') { node ->
      node / gitConfigName('msanmillan')
      node / gitConfigEmail('msanmillan@serbatic.es')
    }
  }
  parameters {
    stringParam('nombre', defaultValue = 'Mario', description = 'Parámetro de cadena para el job')
    choiceParam('ciudad', ['Madrid', 'Sevilla', 'Barcelona', 'Valladolid'])
	booleanParam('agente', false)
  }
  triggers {
    cron('H/20 * * * *') // Se ejecuta cada 20 min
    githubPush()
  }
  steps {
    shell('bash jobscript.sh')
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
