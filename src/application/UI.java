package application;

public class UI {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	
	public static void printANSCIILogo() {
		StringBuilder ANSCIILogo = new StringBuilder();
		ANSCIILogo.append(
				  "\t\t           /$$                         /$$           /$$$$$$$                      /$$      \r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "\t\t          |__/                        | $$          | $$__  $$                    | $$      \r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "\t\t  /$$$$$$$ /$$ /$$$$$$/$$$$   /$$$$$$ | $$  /$$$$$$ | $$  \\ $$  /$$$$$$  /$$$$$$$ | $$   /$$\r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "\t\t /$$_____/| $$| $$_  $$_  $$ /$$__  $$| $$ /$$__  $$| $$$$$$$  |____  $$| $$__  $$| $$  /$$/\r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "\t\t|  $$$$$$ | $$| $$ \\ $$ \\ $$| $$  \\ $$| $$| $$$$$$$$| $$__  $$  /$$$$$$$| $$  \\ $$| $$$$$$/ \r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "\t\t \\____  $$| $$| $$ | $$ | $$| $$  | $$| $$| $$_____/| $$  \\ $$ /$$__  $$| $$  | $$| $$_  $$ \r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "\t\t /$$$$$$$/| $$| $$ | $$ | $$| $$$$$$$/| $$|  $$$$$$$| $$$$$$$/|  $$$$$$$| $$  | $$| $$ \\  $$\r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "\t\t|_______/ |__/|__/ |__/ |__/| $$____/ |__/ \\_______/|_______/  \\_______/|__/  |__/|__/  \\__/\r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "\t\t                            | $$                                                            \r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "\t\t                            | $$                                                            \r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "\t\t                            |__/                                                            \r\n"
				+ "\r\n"
				+ "");
		System.out.println(ANSCIILogo.toString());
	}
	
	public static void mainMenu() {
		StringBuilder options = new StringBuilder();
		UI.printANSCIILogo();
		options.append("\t\t\t\t\t[1] Register an Agency\n"
				+ "\t\t\t\t\t[2] Register an Account\n"
				+ "\t\t\t\t\t[3] Delete an Account\n"
				+ "\t\t\t\t\t[4] Exit\n"
				+ANSI_GREEN+"\t\t\t\t\t>>> "+ANSI_RESET);
		System.out.print(options.toString());
	}
	
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static void printLP() throws InterruptedException {
		StringBuilder sb = new StringBuilder();
		sb.append("\t\t                                                                                                    \r\n"
				+ "\t\t                                                                                                    \r\n"
				+ "\t\t     .@@@@@@#       @@@@@@&,@@@@@@(     &@@@@@&#@@@@@@,   /@@@@@@@*@@@@@@#&@@@@@@     .@@@@@@%      \r\n"
				+ "\t\t     .@@@@@@%       @@@@@@@,@@@@@@@@    @@@@@@&#@@@@@@,  @@@@@@@& ,@@@@@@#@@@@@@@@(   .@@@@@@&      \r\n"
				+ "\t\t     .@@@@@@%       @@@@@@@,@@@@@@@@@%  @@@@@@&#@@@@@@,.@@@@@@@.  ,@@@@@@#@@@@@@@@@@  .@@@@@@&      \r\n"
				+ "\t\t     .@@@@@@%       @@@@@@@,@@@@@@@@@@@.@@@@@@&#@@@@@@@@@@@@@&    ,@@@@@@#@@@@@@@@@@@#.@@@@@@&      \r\n"
				+ "\t\t     .@@@@@@%       @@@@@@@,@@@@@@(@@@@@@@@@@@&#@@@@@@@@@@@@@,    ,@@@@@@#@@@@@@@&@@@@@@@@@@@&      \r\n"
				+ "\t\t     .@@@@@@&****** @@@@@@@,@@@@@@/ #@@@@@@@@@&#@@@@@@,@@@@@@@@   ,@@@@@@#@@@@@@@ ,@@@@@@@@@@&      \r\n"
				+ "\t\t     .@@@@@@@@@@@@@,@@@@@@@,@@@@@@/   @@@@@@@@&#@@@@@@, .@@@@@@@& ,@@@@@@#@@@@@@@   &@@@@@@@@&      \r\n"
				+ "\t\t     .@@@@@@@@@@@@@,@@@@@@@,@@@@@@/    /@@@@@@&#@@@@@@,   @@@@@@@@#@@@@@@#@@@@@@@    .@@@@@@@&      \r\n"
				+ "\t\t     .@@@@@@@@@@@@@@(   @@@@@@@@@@@@@@@@ @@@@@&#@@@@@@@@@@@@@@%      %@@@@@@@@     %@@@@@@@@@*      \r\n"
				+ "\t\t     .@@@@@@@@@@@@@@@@@@@ #@@@@@@@@@@@@@@ ,@@@&#@@@@@@@@@@@@@@@@@@&  %@@@@@@@@    @@@@@@@@@@        \r\n"
				+ "\t\t     .@@@@@@@@@@@@@@@@@@@@* @@@@@@@@@@@@@&  @@&#@@@@@@@@@@@@@@@@@@@@ %@@@@@@@@  .@@@@@@@@@,         \r\n"
				+ "\t\t     .@@@@@@@@#   %@@@@@@@&%@@@@@@@&@@@@@@%  .&#@@@@@@@@    @@@@@@@@ %@@@@@@@@ %@@@@@@@@@           \r\n"
				+ "\t\t     .@@@@@@@@% .(@@@@@@@@@@@@@@@@,,@@@@@@@#   #@@@@@@@@   %@@@@@@@@ %@@@@@@@@@@@@@@@@@.            \r\n"
				+ "\t\t     .@@@@@@@@@@@@@@@@@@@@@@@@@@@%  @@@@@@@@(  #@@@@@@@@@@@@@@@@@@*  %@@@@@@@@@@@@@@@@@             \r\n"
				+ "\t\t     .@@@@@@@@@@@@@@@@@@.@@@@@@@@####@@@@@@@@/ #@@@@@@@@@@@@@@@@,    %@@@@@@@@,@@@@@@@@@&           \r\n"
				+ "\t\t     .@@@@@@@@(        .@@@@@@@@@@@@@@@@@@@@@@,#@@@@@@@@ @@@@@@@@&   %@@@@@@@@  @@@@@@@@@@*         \r\n"
				+ "\t\t     .@@@@@@@@(        @@@@@@@@@@@@@@@@@@@@@@@@%@@@@@@@@  #@@@@@@@@, %@@@@@@@@   ,@@@@@@@@@@        \r\n"
				+ "\t\t     .@@@@@@@@(       @@@@@@@@@.      ,@@@@@@@@@@@@@@@@@   .@@@@@@@@@%@@@@@@@@     &@@@@@@@@@&      \r\n"
				+ "\t\t                                                             @@@@@@@@/                              \r\n"
				+ "\t\t                                                              /@@@@@@/                              \r\n"
				+ "\t\t                                                                @@@@@/                              \r\n"
				+ "\t\t                                                                 &@@@/                              \r\n"
				+ "\t\t                                                                  *@@/                              \r\n"
				+ "\t\t                                                                    @/                              \r\n"
				+ "");
		System.out.println(sb.toString());
		Thread.sleep(1000);
		System.out.print("\t\t\t"+UI.ANSI_GREEN+"♪"+UI.ANSI_RESET+"I've become so "+UI.ANSI_RED+"numb"+UI.ANSI_RESET+", ");
		Thread.sleep(2000);
		System.out.print("I "+UI.ANSI_RED+"can't"+UI.ANSI_RESET+UI.ANSI_YELLOW+" feel"+UI.ANSI_RESET+" you there"+UI.ANSI_GREEN+"♪"+UI.ANSI_RESET);
		Thread.sleep(2500);
		System.out.print("\n"+"\t\t\t"+UI.ANSI_GREEN+"♪"+UI.ANSI_RESET+"Become "+UI.ANSI_RED+"so tired"+UI.ANSI_RESET+", ");
		Thread.sleep(2500);
		System.out.print(UI.ANSI_RED+"so much more aware"+UI.ANSI_RESET+UI.ANSI_GREEN+"♪"+UI.ANSI_RESET);
		Thread.sleep(1000);
		System.out.print("\n"+"\t\t\t"+UI.ANSI_GREEN+"♪"+UI.ANSI_RESET+UI.ANSI_RED+"I'm becoming this"+UI.ANSI_RESET+", ");
		Thread.sleep(2000);
		System.out.print("all I want to do"+UI.ANSI_GREEN+"♪"+UI.ANSI_RESET);
		Thread.sleep(2500);
		System.out.print("\n"+"\t\t\t"+UI.ANSI_GREEN+"♪"+UI.ANSI_RESET+"Is be more like"+UI.ANSI_GREEN+" me"+UI.ANSI_RESET);
		Thread.sleep(1500);
		System.out.print(" and be"+UI.ANSI_YELLOW+" less "+UI.ANSI_RESET);
		Thread.sleep(2000);
		System.out.print(UI.ANSI_RED+"like you"+UI.ANSI_RESET+UI.ANSI_GREEN+"♪"+UI.ANSI_RESET+"\n");
		Thread.sleep(2000);
		System.out.println("\n\t\t\t\t\t Linkin Park - Numb (Meteora - 2003)");
		Thread.sleep(4000);
		System.out.println("You can press enter to return to main menu :)");
	}
}
