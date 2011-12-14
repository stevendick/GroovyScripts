def projects = new File('c:/workspaces/5.1').eachDir { f ->
    if(new File(f, 'src/test/java').exists()) {
      new File(f, 'src/test/groovy').mkdir()
    }
}

