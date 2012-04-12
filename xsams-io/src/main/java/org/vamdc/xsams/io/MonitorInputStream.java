package org.vamdc.xsams.io;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.input.CountingInputStream;

/**
 * An input stream facade that calls monitor event once per each 1MB of data read
 *
 */
class MonitorInputStream extends CountingInputStream{

	private Monitor monitor;
	private final static long MONITOR_STEP=1048576;
	private long nextEvent=0;
	
	
	public MonitorInputStream(InputStream stream, Monitor monitor){
		super(stream);
		this.monitor = monitor;
	}

	/**
	 * Process byte count, call monitor started or tick actions
	 */
	private void event(){
		if (getByteCount()>nextEvent){
			nextEvent+=MONITOR_STEP;
			monitor.tick();
		}else if (getByteCount()==0)
			monitor.started();
	}

	@Override
	public int read() throws IOException{
		if (monitor!=null)
			event();
		return super.read();
	}

	@Override
	public int read(byte[] b) throws IOException{
		if (monitor!=null)
			event();
		return super.read(b);
	}

	@Override
	public int read(byte[] b,int off,int len) throws IOException{
		if (monitor!=null)
			event();
		return super.read(b, off, len);
	}
	
	@Override
	public void close() throws IOException{
		if (monitor!=null)
			monitor.done(super.getByteCount());
		super.close();
	}


}
