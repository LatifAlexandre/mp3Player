// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.3
//
// <auto-generated>
//
// Generated from file `Player.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package MP3Player;

public abstract class _PlayerDisp extends Ice.ObjectImpl implements Player
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::MP3Player::Player"
    };

    public boolean ice_isA(String s)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean ice_isA(String s, Ice.Current __current)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[] ice_ids()
    {
        return __ids;
    }

    public String[] ice_ids(Ice.Current __current)
    {
        return __ids;
    }

    public String ice_id()
    {
        return __ids[1];
    }

    public String ice_id(Ice.Current __current)
    {
        return __ids[1];
    }

    public static String ice_staticId()
    {
        return __ids[1];
    }

    public final void addFile(byte[] f, String name)
    {
        addFile(f, name, null);
    }

    public final void deleteDirectory(String name)
    {
        deleteDirectory(name, null);
    }

    public final void deleteFile(String name)
    {
        deleteFile(name, null);
    }

    public final byte[] getFile(String name)
    {
        return getFile(name, null);
    }

    public final int getFileLength(String name)
    {
        return getFileLength(name, null);
    }

    public final String getFilesByRegex(String name)
    {
        return getFilesByRegex(name, null);
    }

    public final void getInvocationCount(String s)
    {
        getInvocationCount(s, null);
    }

    public static Ice.DispatchStatus ___getInvocationCount(Player __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String s;
        s = __is.readString();
        __inS.endReadParams();
        __obj.getInvocationCount(s, __current);
        __inS.__writeEmptyParams();
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___addFile(Player __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        byte[] f;
        String name;
        f = FileHelper.read(__is);
        name = __is.readString();
        __inS.endReadParams();
        __obj.addFile(f, name, __current);
        __inS.__writeEmptyParams();
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___deleteFile(Player __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String name;
        name = __is.readString();
        __inS.endReadParams();
        __obj.deleteFile(name, __current);
        __inS.__writeEmptyParams();
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___deleteDirectory(Player __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String name;
        name = __is.readString();
        __inS.endReadParams();
        __obj.deleteDirectory(name, __current);
        __inS.__writeEmptyParams();
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getFileLength(Player __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String name;
        name = __is.readString();
        __inS.endReadParams();
        int __ret = __obj.getFileLength(name, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeInt(__ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getFilesByRegex(Player __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String name;
        name = __is.readString();
        __inS.endReadParams();
        String __ret = __obj.getFilesByRegex(name, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeString(__ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getFile(Player __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String name;
        name = __is.readString();
        __inS.endReadParams();
        byte[] __ret = __obj.getFile(name, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        FileHelper.write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "addFile",
        "deleteDirectory",
        "deleteFile",
        "getFile",
        "getFileLength",
        "getFilesByRegex",
        "getInvocationCount",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping"
    };

    public Ice.DispatchStatus __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
        int pos = java.util.Arrays.binarySearch(__all, __current.operation);
        if(pos < 0)
        {
            throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return ___addFile(this, in, __current);
            }
            case 1:
            {
                return ___deleteDirectory(this, in, __current);
            }
            case 2:
            {
                return ___deleteFile(this, in, __current);
            }
            case 3:
            {
                return ___getFile(this, in, __current);
            }
            case 4:
            {
                return ___getFileLength(this, in, __current);
            }
            case 5:
            {
                return ___getFilesByRegex(this, in, __current);
            }
            case 6:
            {
                return ___getInvocationCount(this, in, __current);
            }
            case 7:
            {
                return ___ice_id(this, in, __current);
            }
            case 8:
            {
                return ___ice_ids(this, in, __current);
            }
            case 9:
            {
                return ___ice_isA(this, in, __current);
            }
            case 10:
            {
                return ___ice_ping(this, in, __current);
            }
        }

        assert(false);
        throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
    }

    protected void __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice(ice_staticId(), -1, true);
        __os.endWriteSlice();
    }

    protected void __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        __is.endReadSlice();
    }

    public static final long serialVersionUID = 0L;
}
