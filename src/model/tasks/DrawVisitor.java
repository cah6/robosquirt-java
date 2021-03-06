package model.tasks;

import java.awt.Graphics;
import java.util.ArrayList;

import model.tasks.basictasks.IExecuteTask;
import model.tasks.basictasks.MoveFromExternalTask;
import model.tasks.basictasks.MoveWellToWellTask;
import model.tasks.basictasks.MultiTask;

/**
 * Visitor that handles all drawing of tasks.
 * @author Christian
 *
 */
public class DrawVisitor extends ATaskVisitor {

	public DrawVisitor(){
		addCmd("Multi", new ITaskVisitorCmd(){
			@Override
			public Object apply(String id, IExecuteTask host, Object... params) {
				MultiTask multiHost = (MultiTask) host;
				ArrayList<IExecuteTask> subtasks = multiHost.getSubtasks();
				for (IExecuteTask task : subtasks){
					task.executeVisitor(DrawVisitor.this, params);
				}
				return null;
			}
		});
		addCmd("MoveFromExternal", new ITaskVisitorCmd(){
			@Override
			public Object apply(String id, IExecuteTask host, Object... params) {
				MoveFromExternalTask moveFromExternalHost = (MoveFromExternalTask) host;
				moveFromExternalHost.draw((Graphics) params[0], (Double) params[1]);
				return null;
			}
		});
		addCmd("MoveWellToWell", new ITaskVisitorCmd(){
			@Override
			public Object apply(String id, IExecuteTask host, Object... params) {
				MoveWellToWellTask moveWellToWellHost = (MoveWellToWellTask) host;
				moveWellToWellHost.draw((Graphics) params[0], (Double) params[1]);
				return null;
			}
		});
		addCmd("Dispense", new ITaskVisitorCmd(){
			@Override
			public Object apply(String id, IExecuteTask host, Object... params) {
				return null;
			}
		});
		addCmd("Move", new ITaskVisitorCmd(){
			@Override
			public Object apply(String id, IExecuteTask host, Object... params) {
				return null;
			}
		});
		addCmd("NozzleHeight", new ITaskVisitorCmd(){
			@Override
			public Object apply(String id, IExecuteTask host, Object... params) {
				return null;
			}
		});
	}
}
