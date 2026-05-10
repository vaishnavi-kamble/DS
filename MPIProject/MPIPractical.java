package mpipractical;

import mpi.*;
public class MPIPractical {
public static void main(String args[]) throws Exception {
	
	MPI.Init(args);
	int rank = MPI.COMM_WORLD.Rank(); // processor id
	int size = MPI.COMM_WORLD.Size(); // total processors
	int N = 8; // total elements
	int[] data = new int[N];
	int elementsPerProcess = N / size;
	int[] subArray = new int[elementsPerProcess];
	
	
	// Master process
	if (rank == 0) {
		// initialize array
		for (int i = 0; i < N; i++) {
			 data[i] = i + 1; // 1 to N
			}
		// send parts to other processors
		for (int i = 1; i < size; i++) {
			MPI.COMM_WORLD.Send(data, i * elementsPerProcess,
			elementsPerProcess, MPI.INT, i, 0);
			}
		// copy its own part
		System.arraycopy(data, 0, subArray, 0, elementsPerProcess);
		} else {
		// receive sub array
		MPI.COMM_WORLD.Recv(subArray, 0, elementsPerProcess,
		MPI.INT, 0, 0);
		}
	
		// calculate local sum
		int localSum = 0;
		for (int i = 0; i < elementsPerProcess; i++) {
		localSum += subArray[i];
		}
		
		System.out.println("Process " + rank + " partial sum = " + localSum);
		
		// gather results
		int[] total = new int[1];
		MPI.COMM_WORLD.Reduce(new int[]{localSum}, 0,
		total, 0, 1, MPI.INT, MPI.SUM, 0);
		
		
		// master prints final result
		if (rank == 0) {
			System.out.println("Final Sum = " + total[0]);
		}
		MPI.Finalize();
	}
}