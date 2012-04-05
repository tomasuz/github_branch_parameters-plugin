Jenkins::Plugin::Specification.new do |plugin|
  plugin.name = "github_branch_parameters"
  plugin.display_name = "Github Branch Parameters Plugin"
  plugin.version = '0.0.1'
  plugin.description = 'TODO: enter description here'

  # You should create a wiki-page for your plugin when you publish it, see
  # https://wiki.jenkins-ci.org/display/JENKINS/Hosting+Plugins#HostingPlugins-AddingaWikipage
  # This line makes sure it's listed in your POM.
  plugin.url = 'https://wiki.jenkins-ci.org/display/JENKINS/Github+Branch+Parameters+Plugin'

  # The first argument is your user name for jenkins-ci.org.
  plugin.developed_by "ich", "Frederik Fix <ich@derfred.com>"

  # This specifies where your code is hosted.
  # Alternatives include:
  #  :github => 'myuser/github_branch_parameters-plugin' (without myuser it defaults to jenkinsci)
  #  :git => 'git://repo.or.cz/github_branch_parameters-plugin.git'
  #  :svn => 'https://svn.jenkins-ci.org/trunk/hudson/plugins/github_branch_parameters-plugin'
  plugin.uses_repository :github => "github_branch_parameters-plugin"

  # This is a required dependency for every ruby plugin.
  plugin.depends_on 'ruby-runtime', '0.10'

  # This is a sample dependency for a Jenkins plugin, 'git'.
  #plugin.depends_on 'git', '1.1.11'
end
