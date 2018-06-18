package simulator;

import eduni.simjava.*;

public class DetranSubsystem {

	public static void main(String[] args) {
		Sim_system.initialise();
		Source source = new Source("Source", 50);
		Ticket processor = new Ticket("Ticket", 30);
		Auction auction = new Auction("Auction", 60);
		Habilitation habilitation = new Habilitation("Habilitation", 60);
		Fines fines = new Fines("Fines", 110);
		VehiclesSeizure vehiclesSeizure = new VehiclesSeizure("Vehicles Seizure", 110);
		Licensing licensing = new Licensing("Licensing", 110);
		FristHabilitation fristHabilitation = new FristHabilitation("Frist Habilitation", 110);
		Renovation renovation = new Renovation("Renovation", 110);
		Rate rate = new Rate("Rate", 110);

		Sim_system.link_ports("Source", "Out", "Ticket", "In");
		Sim_system.link_ports("Ticket", "Out1", "Auction", "In");
		Sim_system.link_ports("Ticket", "Out2", "Habilitation", "In");
		Sim_system.link_ports("Ticket", "Out3", "Fines", "In");
		Sim_system.link_ports("Ticket", "Out4", "Vehicles Seizure", "In");
		Sim_system.link_ports("Ticket", "Out5", "Licensing", "In");
		Sim_system.link_ports("Habilitation", "Out1", "Frist Habilitation", "In");
		Sim_system.link_ports("Habilitation", "Out2", "Renovation", "In");
		Sim_system.link_ports("Auction", "Out2", "Fines", "In");
		Sim_system.link_ports("Vehicles Seizure", "Out2", "Fines", "In");
		Sim_system.link_ports("Auction", "Out1", "Rate", "In");
		Sim_system.link_ports("Fines", "Out", "Rate", "In");
		Sim_system.link_ports("Vehicles Seizure", "Out1", "Rate", "In");
		Sim_system.link_ports("Licensing", "Out", "Rate", "In");
		Sim_system.link_ports("Frist Habilitation", "Out", "Rate", "In");
		Sim_system.link_ports("Renovation", "Out", "Rate", "In");
		// Configure trace to the simulator (default, entity, event)
		Sim_system.set_trace_detail(false, true, false);
		// Run the simulation
		Sim_system.run();
	}
}