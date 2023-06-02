	$(document).ready(function(){
	$('ul.tabs li a:first').addClass('active');
		$('.secciones article').hide();
		$('.secciones article:first').show
		//Aqui llamamos a clickar a la a que sería el href
		$('ul.tabs li a').click(function(){
			$('ul.tabs li a').removeClass('active');
			$(this).addClass('active');

			var activeTab= $(this).attr ('href');
			//Aqui te mostraría la otra página
			$(activeTab).show();
		});
	});