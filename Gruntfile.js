/**
 * 
 */

module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    uglify: {
      options: {
        banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
      },
      build: {
        src: 'src/main/webapp/scripts/<%= pkg.name %>.js',
        dest: 'build/<%= pkg.name %>.min.js'
      }
    },
    
    copy: {
	  assets: {
		  expand: true, 
		  src: [
            'node_modules/angular/angular.min.js', 
            'node_modules/angular/angular.min.js.map',
            'node_modules/bootstrap/dist/css/bootstrap.min.css', 
            'node_modules/bootstrap/dist/css/bootstrap.css.map',
            'node_modules/bootstrap/dist/fonts/*', 
            'node_modules/bootstrap/dist/js/bootstrap.min.js',
            'node_modules/angular-route/angular-route.min.js', 
            'node_modules/angular-route/angular-route.min.js.map',
            'node_modules/jquery/dist/jquery.min.js', 
            'node_modules/jquery/dist/jquery.min.map',
            'node_modules/angular-translate/dist/angular-translate.min.js',
            'node_modules/angular-local-storage/dist/angular-local-storage.min.js',
            'node_modules/angular-dynamic-locale/tmhDynamicLocale.min.js', 
            'node_modules/angular-dynamic-locale/tmhDynamicLocale.min.js.map',
            'node_modules/angular-resource/angular-resource.min.js', 
            'node_modules/angular-resource/angular-resource.min.js.map',
            'node_modules/angular-ui-router/release/angular-ui-router.min.js',
            'node_modules/angular-cookies/angular-cookies.min.js', 
            'node_modules/angular-cookies/angular-cookies.min.js.map',
            'node_modules/angular-cache-buster/angular-cache-buster.js',
            'node_modules/angular-translate/dist/angular-translate-storage-cookie/angular-translate-storage-cookie.min.js',
            'node_modules/angular-translate/dist/angular-translate-loader-partial/angular-translate-loader-partial.min.js',
            'node_modules/angular-i18n/*'
            ], 
          dest: 'src/main/webapp/', 
		  filter: 'isFile'
		  
	  },
    }
    
  });

  grunt.loadNpmTasks('grunt-contrib-concat');  
  grunt.loadNpmTasks('grunt-contrib-copy');

  // Default task(s).
  grunt.registerTask('copy-all', ['copy:assets']);

};
