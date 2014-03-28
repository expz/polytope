typedef struct hashelem {
	char* name;
	int index;
	_hashelem* next;
	_hashelem* nextelem;
} _hashelem;
typedef struct hashtable {
	hashelem** table;
	int size;
	int base;
	int count;
	_hashelem* first;
	_hashelem* last;
} hashtable; /* _hashtable */
extern ""C"" {
/** Original signature : <code>hashtable* create_hash_table(int, int)</code> */
	static hashtable* create_hash_table(int size, int base);
	/** Original signature : <code>void free_hash_table(hashtable*)</code> */
	static void free_hash_table(hashtable* ht);
	/** Original signature : <code>hashelem* findhash(const char*, hashtable*)</code> */
	static hashelem* findhash(const char* name, hashtable* ht);
	/** Original signature : <code>hashelem* puthash(const char*, int, hashelem**, hashtable*)</code> */
	static hashelem* puthash(const char* name, int index, hashelem** list, hashtable* ht);
	/** Original signature : <code>void drophash(const char*, hashelem**, hashtable*)</code> */
	static void drophash(const char* name, hashelem** list, hashtable* ht);
	/** Original signature : <code>void free_hash_item(hashelem**)</code> */
	static void free_hash_item(hashelem** hp);
	/** Original signature : <code>hashtable* copy_hash_table(hashtable*, hashelem**, int)</code> */
	static hashtable* copy_hash_table(hashtable* ht, hashelem** list, int newsize);
	/** Original signature : <code>int find_var(lprec*, char*, MYBOOL)</code> */
	static int find_var(lprec* lp, char* name, MYBOOL verbose);
	/** Original signature : <code>int find_row(lprec*, char*, MYBOOL)</code> */
	static int find_row(lprec* lp, char* name, MYBOOL Unconstrained_rows_found);
}
extern ""C"" {
/** Convenience types. */
	typedef unsigned char __u_char;
	typedef unsigned short __u_short;
	typedef unsigned int __u_int;
	typedef unsigned long __u_long;
	/** Fixed-size types, underlying types depend on word size and compiler. */
	typedef signed char __int8_t;
	typedef unsigned char __uint8_t;
	typedef signed short __int16_t;
	typedef unsigned short __uint16_t;
	typedef signed int __int32_t;
	typedef unsigned int __uint32_t;
	typedef signed long long __int64_t;
	typedef unsigned long long __uint64_t;
	typedef long long __quad_t;
	typedef unsigned long long __u_quad_t;
	typedef __u_quad_t __dev_t; /* Type of device numbers.  */
	typedef unsigned int __uid_t; /* Type of user identifications.  */
	typedef unsigned int __gid_t; /* Type of group identifications.  */
	typedef unsigned long __ino_t; /* Type of file serial numbers.  */
	typedef __u_quad_t __ino64_t; /* Type of file serial numbers (LFS).*/
	typedef unsigned int __mode_t; /* Type of file attribute bitmasks.  */
	typedef unsigned int __nlink_t; /* Type of file link counts.  */
	typedef long __off_t; /* Type of file sizes and offsets.  */
	typedef __quad_t __off64_t; /* Type of file sizes and offsets (LFS).  */
	typedef int __pid_t; /* Type of process identifications.  */
	typedef struct __fsid_t {
		int[2] __val; /* Type of file system IDs.  */
	} __fsid_t; /* Type of file system IDs.  */
	typedef long __clock_t; /* Type of CPU usage counts.  */
	typedef unsigned long __rlim_t; /* Type for resource measurement.  */
	typedef __u_quad_t __rlim64_t; /* Type for resource measurement (LFS).  */
	typedef unsigned int __id_t; /* General type for IDs.  */
	typedef long __time_t; /* Seconds since the Epoch.  */
	typedef unsigned int __useconds_t; /* Count of microseconds.  */
	typedef long __suseconds_t; /* Signed count of microseconds.  */
	typedef int __daddr_t; /* The type of a disk address.  */
	typedef int __key_t; /* Type of an IPC key.  */
	/** Clock ID used in clock and timer functions. */
	typedef int __clockid_t;
	/** Timer ID returned by `timer_create'. */
	typedef void* __timer_t;
	/** Type to represent block size. */
	typedef long __blksize_t;
	/** Type to count number of disk blocks. */
	typedef long __blkcnt_t;
	typedef __quad_t __blkcnt64_t;
	/** Type to count file system blocks. */
	typedef unsigned long __fsblkcnt_t;
	typedef __u_quad_t __fsblkcnt64_t;
	/** Type to count file system nodes. */
	typedef unsigned long __fsfilcnt_t;
	typedef __u_quad_t __fsfilcnt64_t;
	/** Type of miscellaneous file system fields. */
	typedef int __fsword_t;
	typedef int __ssize_t; /* Type of a byte count, or error.  */
	/** Signed long type used in system calls. */
	typedef long __syscall_slong_t;
	/** Unsigned long type used in system calls. */
	typedef unsigned long __syscall_ulong_t;
	/**
	 * These few don't really vary by system, they always correspond<br>
	 * to one of the other defined types.
	 */
	typedef __off64_t __loff_t; /* Type of file sizes and offsets (LFS).  */
	typedef __quad_t* __qaddr_t;
	typedef char* __caddr_t;
	/** Duplicates info from stdint.h but this is used in unistd.h. */
	typedef int __intptr_t;
	/** Duplicate info from sys/socket.h. */
	typedef unsigned int __socklen_t;
	typedef __u_char u_char;
	typedef __u_short u_short;
	typedef __u_int u_int;
	typedef __u_long u_long;
	typedef __quad_t quad_t;
	typedef __u_quad_t u_quad_t;
	typedef __fsid_t fsid_t;
	typedef __loff_t loff_t;
	typedef __ino_t ino_t;
	typedef __dev_t dev_t;
	typedef __gid_t gid_t;
	typedef __mode_t mode_t;
	typedef __nlink_t nlink_t;
	typedef __uid_t uid_t;
	typedef __off_t off_t;
	typedef __pid_t pid_t;
	typedef __id_t id_t;
	typedef __ssize_t ssize_t;
	typedef __daddr_t daddr_t;
	typedef __caddr_t caddr_t;
	typedef __key_t key_t;
	/** Returned by `clock'. */
	typedef __clock_t clock_t;
	/** Returned by `time'. */
	typedef __time_t time_t;
	/** Clock ID used in clock and timer functions. */
	typedef __clockid_t clockid_t;
	/** Timer ID returned by `timer_create'. */
	typedef __timer_t timer_t;
	typedef unsigned long size_t;
	/** Old compatibility names for C types. */
	typedef unsigned long ulong;
	typedef unsigned short ushort;
	typedef unsigned int uint;
	typedef char int8_t;
	typedef short int16_t;
	typedef int int32_t;
	typedef long long int64_t;
	/** But these were defined by ISO C without the first `_'. */
	typedef unsigned char u_int8_t;
	typedef unsigned short u_int16_t;
	typedef unsigned int u_int32_t;
	typedef unsigned long long u_int64_t;
	typedef int register_t;
	/** Original signature : <code>__uint64_t __bswap_64(__uint64_t)</code> */
	static __inline __uint64_t __bswap_64(__uint64_t __bsx) {
		return ((((__bsx) & 0xff00000000000000ull) >> 56) | (((__bsx) & 0x00ff000000000000ull) >> 40) | (((__bsx) & 0x0000ff0000000000ull) >> 24) | (((__bsx) & 0x000000ff00000000ull) >> 8) | (((__bsx) & 0x00000000ff000000ull) << 8) | (((__bsx) & 0x0000000000ff0000ull) << 24) | (((__bsx) & 0x000000000000ff00ull) << 40) | (((__bsx) & 0x00000000000000ffull) << 56));
	}
	typedef int __sig_atomic_t;
	typedef struct __sigset_t {
		unsigned long[(1024 / (8 * sizeof(unsigned long)))] __val;
	} __sigset_t;
	typedef __sigset_t sigset_t;
	/**
	 * A time value that is accurate to the nearest<br>
	 * microsecond but also has a range of years.
	 */
	struct timeval {
		__time_t tv_sec; /* Seconds.  */
		__suseconds_t tv_usec; /* Microseconds.  */
	};
	typedef __suseconds_t suseconds_t;
	/** The fd_set member is required to be an array of longs. */
	typedef long __fd_mask;
	/** fd_set for select and pselect. */
	typedef struct fd_set {
		__fd_mask[1024 / (8 * (int)sizeof(__fd_mask))] __fds_bits;
	} fd_set;
	/** Sometimes the fd_set member is assumed to have this type. */
	typedef __fd_mask fd_mask;
	extern ""C"" {
/**
		 * Check the first NFDS descriptors each in READFDS (if not NULL) for read<br>
		 * readiness, in WRITEFDS (if not NULL) for write readiness, and in EXCEPTFDS<br>
		 * (if not NULL) for exceptional conditions.  If TIMEOUT is not NULL, time out<br>
		 * after waiting the interval specified therein.  Returns the number of ready<br>
		 * descriptors, or -1 for errors.<br>
		 * This function is a cancellation point and therefore not marked with<br>
		 * __THROW.<br>
		 * Original signature : <code>int select(int, fd_set*, fd_set*, fd_set*, timeval*)</code>
		 */
		extern int select(int __nfds, fd_set* __readfds, fd_set* __writefds, fd_set* __exceptfds, timeval* __timeout);
		/**
		 * Same as above only that the TIMEOUT value is given with higher<br>
		 * resolution and a sigmask which is been set temporarily.  This version<br>
		 * should be used.<br>
		 * This function is a cancellation point and therefore not marked with<br>
		 * __THROW.<br>
		 * Original signature : <code>int pselect(int, fd_set*, fd_set*, fd_set*, timespec*, const __sigset_t*)</code>
		 */
		extern int pselect(int __nfds, fd_set* __readfds, fd_set* __writefds, fd_set* __exceptfds, timespec* __timeout, const __sigset_t* __sigmask);
	}
	extern ""C"" {
/** Original signature : <code>int gnu_dev_major(unsigned long long)</code> */
		extern unsigned int gnu_dev_major(unsigned long long __dev);
		/** Original signature : <code>int gnu_dev_minor(unsigned long long)</code> */
		extern unsigned int gnu_dev_minor(unsigned long long __dev);
		/** Original signature : <code>long long gnu_dev_makedev(unsigned int, unsigned int)</code> */
		extern unsigned long long gnu_dev_makedev(unsigned int __major, unsigned int __minor);
	}
	typedef __blksize_t blksize_t;
	typedef __blkcnt_t blkcnt_t; /* Type to count number of disk blocks.  */
	typedef __fsblkcnt_t fsblkcnt_t; /* Type to count file system blocks.  */
	typedef __fsfilcnt_t fsfilcnt_t; /* Type to count file system inodes.  */
	/**
	 * Thread identifiers.  The structure of the attribute type is not<br>
	 * exposed on purpose.
	 */
	typedef unsigned long pthread_t;
	union  pthread_attr_t {
		char[36] __size;
		long __align;
	};
	typedef pthread_attr_t pthread_attr_t;
	typedef struct __pthread_slist_t {
		__pthread_internal_slist* __next;
	} __pthread_internal_slist;
	/**
	 * Data structures for mutex handling.  The structure of the attribute<br>
	 * type is not exposed on purpose.
	 */
	typedef union  pthread_mutex_t {
		__pthread_mutex_s __data;
		char[24] __size;
		long __align;
		struct __pthread_mutex_s {
			int __lock;
			unsigned int __count;
			int __owner;
			/**
			 * KIND must stay at this position in the structure to maintain<br>
			 * binary compatibility.
			 */
			int __kind;
			unsigned int __nusers;
			field1_union field1;
			union  field1_union {
				int __spins;
				__pthread_slist_t __list;
			};
		};
	} pthread_mutex_t;
	typedef union  pthread_mutexattr_t {
		char[4] __size;
		int __align;
	} pthread_mutexattr_t;
	/**
	 * Data structure for conditional variable handling.  The structure of<br>
	 * the attribute type is not exposed on purpose.
	 */
	typedef union  pthread_cond_t {
		__data_struct __data;
		char[48] __size;
		long long __align;
		struct __data_struct {
			int __lock;
			unsigned int __futex;
			unsigned long long __total_seq;
			unsigned long long __wakeup_seq;
			unsigned long long __woken_seq;
			void* __mutex;
			unsigned int __nwaiters;
			unsigned int __broadcast_seq;
		};
	} pthread_cond_t;
	typedef union  pthread_condattr_t {
		char[4] __size;
		int __align;
	} pthread_condattr_t;
	/** Keys for thread-specific data */
	typedef unsigned int pthread_key_t;
	/** Once-only execution */
	typedef int pthread_once_t;
	/**
	 * Data structure for read-write lock variable handling.  The<br>
	 * structure of the attribute type is not exposed on purpose.
	 */
	typedef union  pthread_rwlock_t {
		__data_struct __data;
		char[32] __size;
		long __align;
		struct __data_struct {
			int __lock;
			unsigned int __nr_readers;
			unsigned int __readers_wakeup;
			unsigned int __writer_wakeup;
			unsigned int __nr_readers_queued;
			unsigned int __nr_writers_queued;
			/**
			 * FLAGS must stay at this position in the structure to maintain<br>
			 * binary compatibility.
			 */
			unsigned char __flags;
			unsigned char __shared;
			unsigned char __pad1;
			unsigned char __pad2;
			int __writer;
		};
	} pthread_rwlock_t;
	typedef union  pthread_rwlockattr_t {
		char[8] __size;
		long __align;
	} pthread_rwlockattr_t;
	/** POSIX spinlock data type. */
	typedef volatile int pthread_spinlock_t;
	/**
	 * POSIX barriers data type.  The structure of the type is<br>
	 * deliberately not exposed.
	 */
	typedef union  pthread_barrier_t {
		char[20] __size;
		long __align;
	} pthread_barrier_t;
	typedef union  pthread_barrierattr_t {
		char[4] __size;
		int __align;
	} pthread_barrierattr_t;
}
extern ""C"" {
union  wait {
		int w_status;
		__wait_terminated_struct __wait_terminated;
		__wait_stopped_struct __wait_stopped;
		struct __wait_terminated_struct {
			unsigned int __w_termsig:7; /* Terminating signal.  */
			unsigned int __w_coredump:1; /* Set if dumped core.  */
			unsigned int __w_retcode:8; /* Return code if exited normally.  */
			unsigned int :16;
		};
		struct __wait_stopped_struct {
			unsigned int __w_stopval:8; /* W_STOPPED if stopped.  */
			unsigned int __w_stopsig:8; /* Stopping signal.  */
			unsigned int :16;
		};
	};
	/** Returned by `div'. */
	typedef struct div_t {
		int quot; /* Quotient.  */
		int rem; /* Remainder.  */
	} div_t;
	typedef struct ldiv_t {
		long quot; /* Quotient.  */
		long rem; /* Remainder.  */
	} ldiv_t;
	/** Returned by `lldiv'. */
	typedef struct lldiv_t {
		long long quot; /* Quotient.  */
		long long rem; /* Remainder.  */
	} lldiv_t;
	/** Original signature : <code>size_t __ctype_get_mb_cur_max()</code> */
	extern size_t __ctype_get_mb_cur_max();
	/**
	 * Convert a string to a floating-point number.<br>
	 * Original signature : <code>double atof(const char*)</code>
	 */
	extern double atof(const char* __nptr);
	/**
	 * Convert a string to an integer.<br>
	 * Original signature : <code>int atoi(const char*)</code>
	 */
	extern int atoi(const char* __nptr);
	/**
	 * Convert a string to a long integer.<br>
	 * Original signature : <code>long atol(const char*)</code>
	 */
	extern long atol(const char* __nptr);
	/**
	 * Convert a string to a long long integer.<br>
	 * Original signature : <code>long long atoll(const char*)</code>
	 */
	extern long long atoll(const char* __nptr);
	/**
	 * Convert a string to a floating-point number.<br>
	 * Original signature : <code>double strtod(const char*, char**)</code>
	 */
	extern double strtod(const char* __nptr, char** __endptr);
	/**
	 * Likewise for `float' and `long double' sizes of floating-point numbers.<br>
	 * Original signature : <code>float strtof(const char*, char**)</code>
	 */
	extern float strtof(const char* __nptr, char** __endptr);
	/** Original signature : <code>long double strtold(const char*, char**)</code> */
	extern long double strtold(const char* __nptr, char** __endptr);
	/**
	 * Convert a string to a long integer.<br>
	 * Original signature : <code>long strtol(const char*, char**, int)</code>
	 */
	extern long strtol(const char* __nptr, char** __endptr, int __base);
	/**
	 * Convert a string to an unsigned long integer.<br>
	 * Original signature : <code>long strtoul(const char*, char**, int)</code>
	 */
	extern unsigned long strtoul(const char* __nptr, char** __endptr, int __base);
	/** Original signature : <code>long long strtoq(const char*, char**, int)</code> */
	extern long long strtoq(const char* __nptr, char** __endptr, int __base);
	/** Original signature : <code>long long strtouq(const char*, char**, int)</code> */
	extern unsigned long long strtouq(const char* __nptr, char** __endptr, int __base);
	/** Original signature : <code>long long strtoll(const char*, char**, int)</code> */
	extern long long strtoll(const char* __nptr, char** __endptr, int __base);
	/** Original signature : <code>long long strtoull(const char*, char**, int)</code> */
	extern unsigned long long strtoull(const char* __nptr, char** __endptr, int __base);
	/**
	 * Convert N to base 64 using the digits "./0-9A-Za-z", least-significant<br>
	 * digit first.  Returns a pointer to static storage overwritten by the<br>
	 * next call.<br>
	 * Original signature : <code>char* l64a(long)</code>
	 */
	extern char* l64a(long __n);
	/**
	 * Read a number from a string S in base 64 as above.<br>
	 * Original signature : <code>long a64l(const char*)</code>
	 */
	extern long a64l(const char* __s);
	/**
	 * Return a random long integer between 0 and RAND_MAX inclusive.<br>
	 * Original signature : <code>long random()</code>
	 */
	extern long random();
	/**
	 * Seed the random number generator with the given number.<br>
	 * Original signature : <code>void srandom(unsigned int)</code>
	 */
	extern void srandom(unsigned int __seed);
	/**
	 * Initialize the random number generator to use state buffer STATEBUF,<br>
	 * of length STATELEN, and seed it with SEED.  Optimal lengths are 8, 16,<br>
	 * 32, 64, 128 and 256, the bigger the better; values less than 8 will<br>
	 * cause an error and values greater than 256 will be rounded down.<br>
	 * Original signature : <code>char* initstate(unsigned int, char*, size_t)</code>
	 */
	extern char* initstate(unsigned int __seed, char* __statebuf, size_t __statelen);
	/**
	 * Switch the random number generator to state buffer STATEBUF,<br>
	 * which should have been previously initialized by `initstate'.<br>
	 * Original signature : <code>char* setstate(char*)</code>
	 */
	extern char* setstate(char* __statebuf);
	struct random_data {
		int32_t* fptr; /* Front pointer.  */
		int32_t* rptr; /* Rear pointer.  */
		int32_t* state; /* Array of state values.  */
		int rand_type; /* Type of random number generator.  */
		int rand_deg; /* Degree of random number generator.  */
		int rand_sep; /* Distance between front and rear.  */
		int32_t* end_ptr; /* Pointer behind state table.  */
	};
	/** Original signature : <code>int random_r(random_data*, int32_t*)</code> */
	extern int random_r(random_data* __buf, int32_t* __result);
	/** Original signature : <code>int srandom_r(unsigned int, random_data*)</code> */
	extern int srandom_r(unsigned int __seed, random_data* __buf);
	/** Original signature : <code>int initstate_r(unsigned int, char*, size_t, random_data*)</code> */
	extern int initstate_r(unsigned int __seed, char* __statebuf, size_t __statelen, random_data* __buf);
	/** Original signature : <code>int setstate_r(char*, random_data*)</code> */
	extern int setstate_r(char* __statebuf, random_data* __buf);
	/**
	 * Return a random integer between 0 and RAND_MAX inclusive.<br>
	 * Original signature : <code>int rand()</code>
	 */
	extern int rand();
	/**
	 * Seed the random number generator with the given number.<br>
	 * Original signature : <code>void srand(unsigned int)</code>
	 */
	extern void srand(unsigned int __seed);
	/**
	 * Reentrant interface according to POSIX.1.<br>
	 * Original signature : <code>int rand_r(unsigned int*)</code>
	 */
	extern int rand_r(unsigned int* __seed);
	/**
	 * Return non-negative, double-precision floating-point value in [0.0,1.0).<br>
	 * Original signature : <code>double drand48()</code>
	 */
	extern double drand48();
	/** Original signature : <code>double erand48(unsigned short[3])</code> */
	extern double erand48(unsigned short __xsubi[3]);
	/**
	 * Return non-negative, long integer in [0,2^31).<br>
	 * Original signature : <code>long lrand48()</code>
	 */
	extern long lrand48();
	/** Original signature : <code>long nrand48(unsigned short[3])</code> */
	extern long nrand48(unsigned short __xsubi[3]);
	/**
	 * Return signed, long integers in [-2^31,2^31).<br>
	 * Original signature : <code>long mrand48()</code>
	 */
	extern long mrand48();
	/** Original signature : <code>long jrand48(unsigned short[3])</code> */
	extern long jrand48(unsigned short __xsubi[3]);
	/**
	 * Seed random number generator.<br>
	 * Original signature : <code>void srand48(long)</code>
	 */
	extern void srand48(long __seedval);
	/** Original signature : <code>short* seed48(unsigned short[3])</code> */
	extern unsigned short* seed48(unsigned short __seed16v[3]);
	/** Original signature : <code>void lcong48(unsigned short[7])</code> */
	extern void lcong48(unsigned short __param[7]);
	/**
	 * Data structure for communication with thread safe versions.  This<br>
	 * type is to be regarded as opaque.  It's only exported because users<br>
	 * have to allocate objects of this type.
	 */
	struct drand48_data {
		unsigned short[3] __x; /* Current state.  */
		unsigned short[3] __old_x; /* Old state.  */
		unsigned short __c; /* Additive const. in congruential formula.  */
		unsigned short __init; /* Flag for initializing.  */
		unsigned long long __a; /* Factor in congruential formula.  */
	};
	/**
	 * Return non-negative, double-precision floating-point value in [0.0,1.0).<br>
	 * Original signature : <code>int drand48_r(drand48_data*, double*)</code>
	 */
	extern int drand48_r(drand48_data* __buffer, double* __result);
	/** Original signature : <code>int erand48_r(unsigned short[3], drand48_data*, double*)</code> */
	extern int erand48_r(unsigned short __xsubi[3], drand48_data* __buffer, double* __result);
	/**
	 * Return non-negative, long integer in [0,2^31).<br>
	 * Original signature : <code>int lrand48_r(drand48_data*, long*)</code>
	 */
	extern int lrand48_r(drand48_data* __buffer, long* __result);
	/** Original signature : <code>int nrand48_r(unsigned short[3], drand48_data*, long*)</code> */
	extern int nrand48_r(unsigned short __xsubi[3], drand48_data* __buffer, long* __result);
	/**
	 * Return signed, long integers in [-2^31,2^31).<br>
	 * Original signature : <code>int mrand48_r(drand48_data*, long*)</code>
	 */
	extern int mrand48_r(drand48_data* __buffer, long* __result);
	/** Original signature : <code>int jrand48_r(unsigned short[3], drand48_data*, long*)</code> */
	extern int jrand48_r(unsigned short __xsubi[3], drand48_data* __buffer, long* __result);
	/**
	 * Seed random number generator.<br>
	 * Original signature : <code>int srand48_r(long, drand48_data*)</code>
	 */
	extern int srand48_r(long __seedval, drand48_data* __buffer);
	/** Original signature : <code>int seed48_r(unsigned short[3], drand48_data*)</code> */
	extern int seed48_r(unsigned short __seed16v[3], drand48_data* __buffer);
	/** Original signature : <code>int lcong48_r(unsigned short[7], drand48_data*)</code> */
	extern int lcong48_r(unsigned short __param[7], drand48_data* __buffer);
	/**
	 * Allocate SIZE bytes of memory.<br>
	 * Original signature : <code>void* malloc(size_t)</code>
	 */
	extern void* malloc(size_t __size);
	/**
	 * Allocate NMEMB elements of SIZE bytes each, all initialized to 0.<br>
	 * Original signature : <code>void* calloc(size_t, size_t)</code>
	 */
	extern void* calloc(size_t __nmemb, size_t __size);
	/**
	 * __attribute_malloc__ is not used, because if realloc returns<br>
	 * the same pointer that was passed to it, aliasing needs to be allowed<br>
	 * between objects pointed by the old and new pointers.<br>
	 * Original signature : <code>void* realloc(void*, size_t)</code>
	 */
	extern void* realloc(void* __ptr, size_t __size);
	/**
	 * Free a block allocated by `malloc', `realloc' or `calloc'.<br>
	 * Original signature : <code>void free(void*)</code>
	 */
	extern void free(void* __ptr);
	/**
	 * Free a block.  An alias for `free'.	(Sun Unices).<br>
	 * Original signature : <code>void cfree(void*)</code>
	 */
	extern void cfree(void* __ptr);
	extern ""C"" {
/**
		 * Allocate a block that will be freed when the calling function exits.<br>
		 * Original signature : <code>void* alloca(size_t)</code>
		 */
		extern void* alloca(size_t __size);
	}
	/**
	 * Allocate SIZE bytes on a page boundary.  The storage cannot be freed.<br>
	 * Original signature : <code>void* valloc(size_t)</code>
	 */
	extern void* valloc(size_t __size);
	/**
	 * Allocate memory of SIZE bytes with an alignment of ALIGNMENT.<br>
	 * Original signature : <code>int posix_memalign(void**, size_t, size_t)</code>
	 */
	extern int posix_memalign(void** __memptr, size_t __alignment, size_t __size);
	/**
	 * Abort execution and generate a core-dump.<br>
	 * Original signature : <code>void abort()</code>
	 */
	extern void abort();
	/**
	 * Register a function to be called when `exit' is called.<br>
	 * Original signature : <code>int atexit(atexit___func_callback*)</code>
	 */
	extern int atexit(atexit___func_callback* __func);
	/**
	 * Register a function to be called with the status<br>
	 * given to `exit' and the given argument.<br>
	 * Original signature : <code>int on_exit(on_exit___func_callback*, void*)</code>
	 */
	extern int on_exit(on_exit___func_callback* __func, void* __arg);
	/**
	 * Call all functions registered with `atexit' and `on_exit',<br>
	 * in the reverse of the order in which they were registered,<br>
	 * perform stdio cleanup, and terminate program execution with STATUS.<br>
	 * Original signature : <code>void exit(int)</code>
	 */
	extern void exit(int __status);
	/**
	 * Terminate the program with STATUS without calling any of the<br>
	 * functions registered with `atexit' or `on_exit'.<br>
	 * Original signature : <code>void _Exit(int)</code>
	 */
	extern void _Exit(int __status);
	/**
	 * Return the value of envariable NAME, or NULL if it doesn't exist.<br>
	 * Original signature : <code>char* getenv(const char*)</code>
	 */
	extern char* getenv(const char* __name);
	/**
	 * Put STRING, which is of the form "NAME=VALUE", in the environment.<br>
	 * If there is no `=', remove NAME from the environment.<br>
	 * Original signature : <code>int putenv(char*)</code>
	 */
	extern int putenv(char* __string);
	/**
	 * Set NAME to VALUE in the environment.<br>
	 * If REPLACE is nonzero, overwrite an existing value.<br>
	 * Original signature : <code>int setenv(const char*, const char*, int)</code>
	 */
	extern int setenv(const char* __name, const char* __value, int __replace);
	/**
	 * Remove the variable NAME from the environment.<br>
	 * Original signature : <code>int unsetenv(const char*)</code>
	 */
	extern int unsetenv(const char* __name);
	/**
	 * The `clearenv' was planned to be added to POSIX.1 but probably<br>
	 * never made it.  Nevertheless the POSIX.9 standard (POSIX bindings<br>
	 * for Fortran 77) requires this function.<br>
	 * Original signature : <code>int clearenv()</code>
	 */
	extern int clearenv();
	/**
	 * Generate a unique temporary file name from TEMPLATE.<br>
	 * The last six characters of TEMPLATE must be "XXXXXX";<br>
	 * they are replaced with a string that makes the file name unique.<br>
	 * Always returns TEMPLATE, it's either a temporary file name or a null<br>
	 * string if it cannot get a unique file name.<br>
	 * Original signature : <code>char* mktemp(char*)</code>
	 */
	extern char* mktemp(char* __template);
	/** Original signature : <code>int mkstemp(char*)</code> */
	extern int mkstemp(char* __template);
	/** Original signature : <code>int mkstemps(char*, int)</code> */
	extern int mkstemps(char* __template, int __suffixlen);
	/**
	 * Create a unique temporary directory from TEMPLATE.<br>
	 * The last six characters of TEMPLATE must be "XXXXXX";<br>
	 * they are replaced with a string that makes the directory name unique.<br>
	 * Returns TEMPLATE, or a null pointer if it cannot get a unique name.<br>
	 * The directory is created mode 700.<br>
	 * Original signature : <code>char* mkdtemp(char*)</code>
	 */
	extern char* mkdtemp(char* __template);
	/**
	 * Execute the given line as a shell command.<br>
	 * This function is a cancellation point and therefore not marked with<br>
	 * __THROW.<br>
	 * Original signature : <code>int system(const char*)</code>
	 */
	extern int system(const char* __command);
	/**
	 * Return the canonical absolute name of file NAME.  If RESOLVED is<br>
	 * null, the result is malloc'd; otherwise, if the canonical name is<br>
	 * PATH_MAX chars or more, returns null with `errno' set to<br>
	 * ENAMETOOLONG; if the name fits in fewer than PATH_MAX chars,<br>
	 * returns the name in RESOLVED.<br>
	 * Original signature : <code>char* realpath(const char*, char*)</code>
	 */
	extern char* realpath(const char* __name, char* __resolved);
	typedef int (*__compar_fn_t)(const void* voidPtr1, const void* voidPtr2) __compar_fn_t;
	/**
	 * Do a binary search for KEY in BASE, which consists of NMEMB elements<br>
	 * of SIZE bytes each, using COMPAR to perform the comparisons.<br>
	 * Original signature : <code>void* bsearch(const void*, const void*, size_t, size_t, __compar_fn_t)</code>
	 */
	extern void* bsearch(const void* __key, const void* __base, size_t __nmemb, size_t __size, __compar_fn_t __compar);
	/**
	 * Sort NMEMB elements of BASE, of SIZE bytes each,<br>
	 * using COMPAR to perform the comparisons.<br>
	 * Original signature : <code>void qsort(void*, size_t, size_t, __compar_fn_t)</code>
	 */
	extern void qsort(void* __base, size_t __nmemb, size_t __size, __compar_fn_t __compar);
	/**
	 * Return the absolute value of X.<br>
	 * Original signature : <code>int abs(int)</code>
	 */
	extern int abs(int __x);
	/** Original signature : <code>long labs(long)</code> */
	extern long labs(long __x);
	/** Original signature : <code>long long llabs(long long)</code> */
	extern long long llabs(long long __x);
	/**
	 * GCC may have built-ins for these someday.<br>
	 * Original signature : <code>div_t div(int, int)</code>
	 */
	extern div_t div(int __numer, int __denom);
	/** Original signature : <code>ldiv_t ldiv(long, long)</code> */
	extern ldiv_t ldiv(long __numer, long __denom);
	/** Original signature : <code>lldiv_t lldiv(long long, long long)</code> */
	extern lldiv_t lldiv(long long __numer, long long __denom);
	/**
	 * Convert VALUE to a string with NDIGIT digits and return a pointer to<br>
	 * this.  Set *DECPT with the position of the decimal character and *SIGN<br>
	 * with the sign of the number.<br>
	 * Original signature : <code>char* ecvt(double, int, int*, int*)</code>
	 */
	extern char* ecvt(double __value, int __ndigit, int* __decpt, int* __sign);
	/**
	 * Convert VALUE to a string rounded to NDIGIT decimal digits.  Set *DECPT<br>
	 * with the position of the decimal character and *SIGN with the sign of<br>
	 * the number.<br>
	 * Original signature : <code>char* fcvt(double, int, int*, int*)</code>
	 */
	extern char* fcvt(double __value, int __ndigit, int* __decpt, int* __sign);
	/**
	 * If possible convert VALUE to a string with NDIGIT significant digits.<br>
	 * Otherwise use exponential representation.  The resulting string will<br>
	 * be written to BUF.<br>
	 * Original signature : <code>char* gcvt(double, int, char*)</code>
	 */
	extern char* gcvt(double __value, int __ndigit, char* __buf);
	/**
	 * Long double versions of above functions.<br>
	 * Original signature : <code>char* qecvt(long double, int, int*, int*)</code>
	 */
	extern char* qecvt(long double __value, int __ndigit, int* __decpt, int* __sign);
	/** Original signature : <code>char* qfcvt(long double, int, int*, int*)</code> */
	extern char* qfcvt(long double __value, int __ndigit, int* __decpt, int* __sign);
	/** Original signature : <code>char* qgcvt(long double, int, char*)</code> */
	extern char* qgcvt(long double __value, int __ndigit, char* __buf);
	/**
	 * Reentrant version of the functions above which provide their own<br>
	 * buffers.<br>
	 * Original signature : <code>int ecvt_r(double, int, int*, int*, char*, size_t)</code>
	 */
	extern int ecvt_r(double __value, int __ndigit, int* __decpt, int* __sign, char* __buf, size_t __len);
	/** Original signature : <code>int fcvt_r(double, int, int*, int*, char*, size_t)</code> */
	extern int fcvt_r(double __value, int __ndigit, int* __decpt, int* __sign, char* __buf, size_t __len);
	/** Original signature : <code>int qecvt_r(long double, int, int*, int*, char*, size_t)</code> */
	extern int qecvt_r(long double __value, int __ndigit, int* __decpt, int* __sign, char* __buf, size_t __len);
	/** Original signature : <code>int qfcvt_r(long double, int, int*, int*, char*, size_t)</code> */
	extern int qfcvt_r(long double __value, int __ndigit, int* __decpt, int* __sign, char* __buf, size_t __len);
	/**
	 * Return the length of the multibyte character<br>
	 * in S, which is no longer than N.<br>
	 * Original signature : <code>int mblen(const char*, size_t)</code>
	 */
	extern int mblen(const char* __s, size_t __n);
	/**
	 * Return the length of the given multibyte character,<br>
	 * putting its `wchar_t' representation in *PWC.<br>
	 * Original signature : <code>int mbtowc(wchar_t*, const char*, size_t)</code>
	 */
	extern int mbtowc(wchar_t* __pwc, const char* __s, size_t __n);
	/**
	 * Put the multibyte character represented<br>
	 * by WCHAR in S, returning its length.<br>
	 * Original signature : <code>int wctomb(char*, wchar_t)</code>
	 */
	extern int wctomb(char* __s, wchar_t __wchar);
	/**
	 * Convert a multibyte string to a wide char string.<br>
	 * Original signature : <code>size_t mbstowcs(wchar_t*, const char*, size_t)</code>
	 */
	extern size_t mbstowcs(wchar_t* __pwcs, const char* __s, size_t __n);
	/**
	 * Convert a wide char string to multibyte string.<br>
	 * Original signature : <code>size_t wcstombs(char*, const wchar_t*, size_t)</code>
	 */
	extern size_t wcstombs(char* __s, const wchar_t* __pwcs, size_t __n);
	/**
	 * Determine whether the string value of RESPONSE matches the affirmation<br>
	 * or negative response expression as specified by the LC_MESSAGES category<br>
	 * in the program's current locale.  Returns 1 if affirmative, 0 if<br>
	 * negative, and -1 if not matching.<br>
	 * Original signature : <code>int rpmatch(const char*)</code>
	 */
	extern int rpmatch(const char* __response);
	/**
	 * Parse comma separated suboption from *OPTIONP and match against<br>
	 * strings in TOKENS.  If found return index and set *VALUEP to<br>
	 * optional value introduced by an equal sign.  If the suboption is<br>
	 * not part of TOKENS return in *VALUEP beginning of unknown<br>
	 * suboption.  On exit *OPTIONP is set to the beginning of the next<br>
	 * token or at the terminating NUL character.<br>
	 * Original signature : <code>int getsubopt(char**, const char**, char**)</code>
	 */
	extern int getsubopt(char** __optionp, const char** __tokens, char** __valuep);
	/**
	 * Put the 1 minute, 5 minute and 15 minute load averages into the first<br>
	 * NELEM elements of LOADAVG.  Return the number written (never more than<br>
	 * three, but may be less than NELEM), or -1 if an error occurred.<br>
	 * Original signature : <code>int getloadavg(double[], int)</code>
	 */
	extern int getloadavg(double __loadavg[], int __nelem);
	typedef void atexit___func_callback();
	typedef void on_exit___func_callback(int __status, void* __arg);
}
extern ""C"" {
/**
	 * Copy N bytes of SRC to DEST.<br>
	 * Original signature : <code>void* memcpy(void*, const void*, size_t)</code>
	 */
	extern void* memcpy(void* __dest, const void* __src, size_t __n);
	/**
	 * Copy N bytes of SRC to DEST, guaranteeing<br>
	 * correct behavior for overlapping strings.<br>
	 * Original signature : <code>void* memmove(void*, const void*, size_t)</code>
	 */
	extern void* memmove(void* __dest, const void* __src, size_t __n);
	/** Original signature : <code>void* memccpy(void*, const void*, int, size_t)</code> */
	extern void* memccpy(void* __dest, const void* __src, int __c, size_t __n);
	/**
	 * Set N bytes of S to C.<br>
	 * Original signature : <code>void* memset(void*, int, size_t)</code>
	 */
	extern void* memset(void* __s, int __c, size_t __n);
	/**
	 * Compare N bytes of S1 and S2.<br>
	 * Original signature : <code>int memcmp(const void*, const void*, size_t)</code>
	 */
	extern int memcmp(const void* __s1, const void* __s2, size_t __n);
	/** Original signature : <code>void* memchr(const void*, int, size_t)</code> */
	extern void* memchr(const void* __s, int __c, size_t __n);
	/**
	 * Copy SRC to DEST.<br>
	 * Original signature : <code>char* strcpy(char*, const char*)</code>
	 */
	extern char* strcpy(char* __dest, const char* __src);
	/**
	 * Copy no more than N characters of SRC to DEST.<br>
	 * Original signature : <code>char* strncpy(char*, const char*, size_t)</code>
	 */
	extern char* strncpy(char* __dest, const char* __src, size_t __n);
	/**
	 * Append SRC onto DEST.<br>
	 * Original signature : <code>char* strcat(char*, const char*)</code>
	 */
	extern char* strcat(char* __dest, const char* __src);
	/**
	 * Append no more than N characters from SRC onto DEST.<br>
	 * Original signature : <code>char* strncat(char*, const char*, size_t)</code>
	 */
	extern char* strncat(char* __dest, const char* __src, size_t __n);
	/**
	 * Compare S1 and S2.<br>
	 * Original signature : <code>int strcmp(const char*, const char*)</code>
	 */
	extern int strcmp(const char* __s1, const char* __s2);
	/**
	 * Compare N characters of S1 and S2.<br>
	 * Original signature : <code>int strncmp(const char*, const char*, size_t)</code>
	 */
	extern int strncmp(const char* __s1, const char* __s2, size_t __n);
	/**
	 * Compare the collated forms of S1 and S2.<br>
	 * Original signature : <code>int strcoll(const char*, const char*)</code>
	 */
	extern int strcoll(const char* __s1, const char* __s2);
	/**
	 * Put a transformation of SRC into no more than N bytes of DEST.<br>
	 * Original signature : <code>size_t strxfrm(char*, const char*, size_t)</code>
	 */
	extern size_t strxfrm(char* __dest, const char* __src, size_t __n);
	/**
	 * Structure for reentrant locale using functions.  This is an<br>
	 * (almost) opaque type for the user level programs.  The file and<br>
	 * this data structure is not standardized.  Don't rely on it.  It can<br>
	 * go away without warning.
	 */
	typedef __locale_struct* __locale_t;
	/** POSIX 2008 makes locale_t official. */
	typedef __locale_t locale_t;
	/**
	 * Compare the collated forms of S1 and S2 using rules from L.<br>
	 * Original signature : <code>int strcoll_l(const char*, const char*, __locale_t)</code>
	 */
	extern int strcoll_l(const char* __s1, const char* __s2, __locale_t __l);
	/**
	 * Put a transformation of SRC into no more than N bytes of DEST.<br>
	 * Original signature : <code>size_t strxfrm_l(char*, const char*, size_t, __locale_t)</code>
	 */
	extern size_t strxfrm_l(char* __dest, const char* __src, size_t __n, __locale_t __l);
	/**
	 * Duplicate S, returning an identical malloc'd string.<br>
	 * Original signature : <code>char* strdup(const char*)</code>
	 */
	extern char* strdup(const char* __s);
	/** Original signature : <code>char* strndup(const char*, size_t)</code> */
	extern char* strndup(const char* __string, size_t __n);
	/** Original signature : <code>char* strchr(const char*, int)</code> */
	extern char* strchr(const char* __s, int __c);
	/** Original signature : <code>char* strrchr(const char*, int)</code> */
	extern char* strrchr(const char* __s, int __c);
	/**
	 * Return the length of the initial segment of S which<br>
	 * consists entirely of characters not in REJECT.<br>
	 * Original signature : <code>size_t strcspn(const char*, const char*)</code>
	 */
	extern size_t strcspn(const char* __s, const char* __reject);
	/**
	 * Return the length of the initial segment of S which<br>
	 * consists entirely of characters in ACCEPT.<br>
	 * Original signature : <code>size_t strspn(const char*, const char*)</code>
	 */
	extern size_t strspn(const char* __s, const char* __accept);
	/** Original signature : <code>char* strpbrk(const char*, const char*)</code> */
	extern char* strpbrk(const char* __s, const char* __accept);
	/** Original signature : <code>char* strstr(const char*, const char*)</code> */
	extern char* strstr(const char* __haystack, const char* __needle);
	/**
	 * Divide S into tokens separated by characters in DELIM.<br>
	 * Original signature : <code>char* strtok(char*, const char*)</code>
	 */
	extern char* strtok(char* __s, const char* __delim);
	/**
	 * Divide S into tokens separated by characters in DELIM.  Information<br>
	 * passed between calls are stored in SAVE_PTR.<br>
	 * Original signature : <code>char* __strtok_r(char*, const char*, char**)</code>
	 */
	extern char* __strtok_r(char* __s, const char* __delim, char** __save_ptr);
	/** Original signature : <code>char* strtok_r(char*, const char*, char**)</code> */
	extern char* strtok_r(char* __s, const char* __delim, char** __save_ptr);
	/**
	 * Return the length of S.<br>
	 * Original signature : <code>size_t strlen(const char*)</code>
	 */
	extern size_t strlen(const char* __s);
	/**
	 * Find the length of STRING, but scan at most MAXLEN characters.<br>
	 * If no '\0' terminator is found in that many characters, return MAXLEN.<br>
	 * Original signature : <code>size_t strnlen(const char*, size_t)</code>
	 */
	extern size_t strnlen(const char* __string, size_t __maxlen);
	/**
	 * Return a string describing the meaning of the `errno' code in ERRNUM.<br>
	 * Original signature : <code>char* strerror(int)</code>
	 */
	extern char* strerror(int __errnum);
	/** Original signature : <code>int __xpg_strerror_r(int, char*, size_t)</code> */
	extern int __xpg_strerror_r(int __errnum, char* __buf, size_t __buflen);
	/**
	 * Translate error number to string according to the locale L.<br>
	 * Original signature : <code>char* strerror_l(int, __locale_t)</code>
	 */
	extern char* strerror_l(int __errnum, __locale_t __l);
	/**
	 * We define this function always since `bzero' is sometimes needed when<br>
	 * the namespace rules does not allow this.<br>
	 * Original signature : <code>void __bzero(void*, size_t)</code>
	 */
	extern void __bzero(void* __s, size_t __n);
	/**
	 * Copy N bytes of SRC to DEST (like memmove, but args reversed).<br>
	 * Original signature : <code>void bcopy(const void*, void*, size_t)</code>
	 */
	extern void bcopy(const void* __src, void* __dest, size_t __n);
	/**
	 * Set N bytes of S to 0.<br>
	 * Original signature : <code>void bzero(void*, size_t)</code>
	 */
	extern void bzero(void* __s, size_t __n);
	/**
	 * Compare N bytes of S1 and S2 (same as memcmp).<br>
	 * Original signature : <code>int bcmp(const void*, const void*, size_t)</code>
	 */
	extern int bcmp(const void* __s1, const void* __s2, size_t __n);
	/** Original signature : <code>char* index(const char*, int)</code> */
	extern char* index(const char* __s, int __c);
	/** Original signature : <code>char* rindex(const char*, int)</code> */
	extern char* rindex(const char* __s, int __c);
	/**
	 * Return the position of the first bit set in I, or 0 if none are set.<br>
	 * The least-significant bit is position 1, the most-significant 32.<br>
	 * Original signature : <code>int ffs(int)</code>
	 */
	extern int ffs(int __i);
	/**
	 * Compare S1 and S2, ignoring case.<br>
	 * Original signature : <code>int strcasecmp(const char*, const char*)</code>
	 */
	extern int strcasecmp(const char* __s1, const char* __s2);
	/**
	 * Compare no more than N chars of S1 and S2, ignoring case.<br>
	 * Original signature : <code>int strncasecmp(const char*, const char*, size_t)</code>
	 */
	extern int strncasecmp(const char* __s1, const char* __s2, size_t __n);
	/**
	 * Return the next DELIM-delimited token from *STRINGP,<br>
	 * terminating it with a '\0', and update *STRINGP to point past it.<br>
	 * Original signature : <code>char* strsep(char**, const char*)</code>
	 */
	extern char* strsep(char** __stringp, const char* __delim);
	/**
	 * Return a string describing the meaning of the signal number in SIG.<br>
	 * Original signature : <code>char* strsignal(int)</code>
	 */
	extern char* strsignal(int __sig);
	/**
	 * Copy SRC to DEST, returning the address of the terminating '\0' in DEST.<br>
	 * Original signature : <code>char* __stpcpy(char*, const char*)</code>
	 */
	extern char* __stpcpy(char* __dest, const char* __src);
	/** Original signature : <code>char* stpcpy(char*, const char*)</code> */
	extern char* stpcpy(char* __dest, const char* __src);
	/**
	 * Copy no more than N characters of SRC to DEST, returning the address of<br>
	 * the last character written into DEST.<br>
	 * Original signature : <code>char* __stpncpy(char*, const char*, size_t)</code>
	 */
	extern char* __stpncpy(char* __dest, const char* __src, size_t __n);
	/** Original signature : <code>char* stpncpy(char*, const char*, size_t)</code> */
	extern char* stpncpy(char* __dest, const char* __src, size_t __n);
	struct __locale_struct {
		/** Note: LC_ALL is not a valid index into this array. */
		__locale_data*[13] __locales; /* 13 = __LC_LAST. */
		/** To increase the speed of this solution we add some special members. */
		const unsigned short* __ctype_b;
		const int* __ctype_tolower;
		const int* __ctype_toupper;
		/** Note: LC_ALL is not a valid index into this array. */
		const char*[13] __names;
	};
}
extern ""C"" {
/**
	 * The ix87 FPUs evaluate all values in the 80 bit floating-point format<br>
	 * which is also available for the user as `long double'.  Therefore we<br>
	 * define:
	 */
	typedef long double float_t; /* `float' expressions are evaluated as
				   `long double'.  */
	typedef long double double_t; /* `double' expressions are evaluated as
				   `long double'.  */
	/**
	 * Arc cosine of X.<br>
	 * Original signature : <code>double acos(double)</code>
	 */
	extern double acos(double __x);
	/** Original signature : <code>double __acos(double)</code> */
	extern double __acos(double __x);
	/**
	 * Arc sine of X.<br>
	 * Original signature : <code>double asin(double)</code>
	 */
	extern double asin(double __x);
	/** Original signature : <code>double __asin(double)</code> */
	extern double __asin(double __x);
	/**
	 * Arc tangent of X.<br>
	 * Original signature : <code>double atan(double)</code>
	 */
	extern double atan(double __x);
	/** Original signature : <code>double __atan(double)</code> */
	extern double __atan(double __x);
	/**
	 * Arc tangent of Y/X.<br>
	 * Original signature : <code>double atan2(double, double)</code>
	 */
	extern double atan2(double __y, double __x);
	/** Original signature : <code>double __atan2(double, double)</code> */
	extern double __atan2(double __y, double __x);
	/**
	 * Cosine of X.<br>
	 * Original signature : <code>double cos(double)</code>
	 */
	extern double cos(double __x);
	/** Original signature : <code>double __cos(double)</code> */
	extern double __cos(double __x);
	/**
	 * Sine of X.<br>
	 * Original signature : <code>double sin(double)</code>
	 */
	extern double sin(double __x);
	/** Original signature : <code>double __sin(double)</code> */
	extern double __sin(double __x);
	/**
	 * Tangent of X.<br>
	 * Original signature : <code>double tan(double)</code>
	 */
	extern double tan(double __x);
	/** Original signature : <code>double __tan(double)</code> */
	extern double __tan(double __x);
	/**
	 * Hyperbolic cosine of X.<br>
	 * Original signature : <code>double cosh(double)</code>
	 */
	extern double cosh(double __x);
	/** Original signature : <code>double __cosh(double)</code> */
	extern double __cosh(double __x);
	/**
	 * Hyperbolic sine of X.<br>
	 * Original signature : <code>double sinh(double)</code>
	 */
	extern double sinh(double __x);
	/** Original signature : <code>double __sinh(double)</code> */
	extern double __sinh(double __x);
	/**
	 * Hyperbolic tangent of X.<br>
	 * Original signature : <code>double tanh(double)</code>
	 */
	extern double tanh(double __x);
	/** Original signature : <code>double __tanh(double)</code> */
	extern double __tanh(double __x);
	/**
	 * Hyperbolic arc cosine of X.<br>
	 * Original signature : <code>double acosh(double)</code>
	 */
	extern double acosh(double __x);
	/** Original signature : <code>double __acosh(double)</code> */
	extern double __acosh(double __x);
	/**
	 * Hyperbolic arc sine of X.<br>
	 * Original signature : <code>double asinh(double)</code>
	 */
	extern double asinh(double __x);
	/** Original signature : <code>double __asinh(double)</code> */
	extern double __asinh(double __x);
	/**
	 * Hyperbolic arc tangent of X.<br>
	 * Original signature : <code>double atanh(double)</code>
	 */
	extern double atanh(double __x);
	/** Original signature : <code>double __atanh(double)</code> */
	extern double __atanh(double __x);
	/**
	 * Exponential function of X.<br>
	 * Original signature : <code>double exp(double)</code>
	 */
	extern double exp(double __x);
	/** Original signature : <code>double __exp(double)</code> */
	extern double __exp(double __x);
	/**
	 * Break VALUE into a normalized fraction and an integral power of 2.<br>
	 * Original signature : <code>double frexp(double, int*)</code>
	 */
	extern double frexp(double __x, int* __exponent);
	/** Original signature : <code>double __frexp(double, int*)</code> */
	extern double __frexp(double __x, int* __exponent);
	/**
	 * X times (two to the EXP power).<br>
	 * Original signature : <code>double ldexp(double, int)</code>
	 */
	extern double ldexp(double __x, int __exponent);
	/** Original signature : <code>double __ldexp(double, int)</code> */
	extern double __ldexp(double __x, int __exponent);
	/**
	 * Natural logarithm of X.<br>
	 * Original signature : <code>double log(double)</code>
	 */
	extern double log(double __x);
	/** Original signature : <code>double __log(double)</code> */
	extern double __log(double __x);
	/**
	 * Base-ten logarithm of X.<br>
	 * Original signature : <code>double log10(double)</code>
	 */
	extern double log10(double __x);
	/** Original signature : <code>double __log10(double)</code> */
	extern double __log10(double __x);
	/**
	 * Break VALUE into integral and fractional parts.<br>
	 * Original signature : <code>double modf(double, double*)</code>
	 */
	extern double modf(double __x, double* __iptr);
	/** Original signature : <code>double __modf(double, double*)</code> */
	extern double __modf(double __x, double* __iptr);
	/**
	 * Return exp(X) - 1.<br>
	 * Original signature : <code>double expm1(double)</code>
	 */
	extern double expm1(double __x);
	/** Original signature : <code>double __expm1(double)</code> */
	extern double __expm1(double __x);
	/**
	 * Return log(1 + X).<br>
	 * Original signature : <code>double log1p(double)</code>
	 */
	extern double log1p(double __x);
	/** Original signature : <code>double __log1p(double)</code> */
	extern double __log1p(double __x);
	/**
	 * Return the base 2 signed integral exponent of X.<br>
	 * Original signature : <code>double logb(double)</code>
	 */
	extern double logb(double __x);
	/** Original signature : <code>double __logb(double)</code> */
	extern double __logb(double __x);
	/**
	 * Compute base-2 exponential of X.<br>
	 * Original signature : <code>double exp2(double)</code>
	 */
	extern double exp2(double __x);
	/** Original signature : <code>double __exp2(double)</code> */
	extern double __exp2(double __x);
	/**
	 * Compute base-2 logarithm of X.<br>
	 * Original signature : <code>double log2(double)</code>
	 */
	extern double log2(double __x);
	/** Original signature : <code>double __log2(double)</code> */
	extern double __log2(double __x);
	/**
	 * Return X to the Y power.<br>
	 * Original signature : <code>double pow(double, double)</code>
	 */
	extern double pow(double __x, double __y);
	/** Original signature : <code>double __pow(double, double)</code> */
	extern double __pow(double __x, double __y);
	/**
	 * Return the square root of X.<br>
	 * Original signature : <code>double sqrt(double)</code>
	 */
	extern double sqrt(double __x);
	/** Original signature : <code>double __sqrt(double)</code> */
	extern double __sqrt(double __x);
	/**
	 * Return `sqrt(X*X + Y*Y)'.<br>
	 * Original signature : <code>double hypot(double, double)</code>
	 */
	extern double hypot(double __x, double __y);
	/** Original signature : <code>double __hypot(double, double)</code> */
	extern double __hypot(double __x, double __y);
	/**
	 * Return the cube root of X.<br>
	 * Original signature : <code>double cbrt(double)</code>
	 */
	extern double cbrt(double __x);
	/** Original signature : <code>double __cbrt(double)</code> */
	extern double __cbrt(double __x);
	/**
	 * Smallest integral value not less than X.<br>
	 * Original signature : <code>double ceil(double)</code>
	 */
	extern double ceil(double __x);
	/** Original signature : <code>double __ceil(double)</code> */
	extern double __ceil(double __x);
	/**
	 * Absolute value of X.<br>
	 * Original signature : <code>double fabs(double)</code>
	 */
	extern double fabs(double __x);
	/** Original signature : <code>double __fabs(double)</code> */
	extern double __fabs(double __x);
	/**
	 * Largest integer not greater than X.<br>
	 * Original signature : <code>double floor(double)</code>
	 */
	extern double floor(double __x);
	/** Original signature : <code>double __floor(double)</code> */
	extern double __floor(double __x);
	/**
	 * Floating-point modulo remainder of X/Y.<br>
	 * Original signature : <code>double fmod(double, double)</code>
	 */
	extern double fmod(double __x, double __y);
	/** Original signature : <code>double __fmod(double, double)</code> */
	extern double __fmod(double __x, double __y);
	/**
	 * Return 0 if VALUE is finite or NaN, +1 if it<br>
	 * is +Infinity, -1 if it is -Infinity.<br>
	 * Original signature : <code>int __isinf(double)</code>
	 */
	extern int __isinf(double __value);
	/**
	 * Return nonzero if VALUE is finite and not NaN.<br>
	 * Original signature : <code>int __finite(double)</code>
	 */
	extern int __finite(double __value);
	/**
	 * Return 0 if VALUE is finite or NaN, +1 if it<br>
	 * is +Infinity, -1 if it is -Infinity.<br>
	 * Original signature : <code>int isinf(double)</code>
	 */
	extern int isinf(double __value);
	/**
	 * Return nonzero if VALUE is finite and not NaN.<br>
	 * Original signature : <code>int finite(double)</code>
	 */
	extern int finite(double __value);
	/**
	 * Return the remainder of X/Y.<br>
	 * Original signature : <code>double drem(double, double)</code>
	 */
	extern double drem(double __x, double __y);
	/** Original signature : <code>double __drem(double, double)</code> */
	extern double __drem(double __x, double __y);
	/**
	 * Return the fractional part of X after dividing out `ilogb (X)'.<br>
	 * Original signature : <code>double significand(double)</code>
	 */
	extern double significand(double __x);
	/** Original signature : <code>double __significand(double)</code> */
	extern double __significand(double __x);
	/**
	 * Return X with its signed changed to Y's.<br>
	 * Original signature : <code>double copysign(double, double)</code>
	 */
	extern double copysign(double __x, double __y);
	/** Original signature : <code>double __copysign(double, double)</code> */
	extern double __copysign(double __x, double __y);
	/**
	 * Return representation of NaN for double type.<br>
	 * Original signature : <code>double nan(const char*)</code>
	 */
	extern double nan(const char* __tagb);
	/** Original signature : <code>double __nan(const char*)</code> */
	extern double __nan(const char* __tagb);
	/**
	 * Return nonzero if VALUE is not a number.<br>
	 * Original signature : <code>int __isnan(double)</code>
	 */
	extern int __isnan(double __value);
	/**
	 * Return nonzero if VALUE is not a number.<br>
	 * Original signature : <code>int isnan(double)</code>
	 */
	extern int isnan(double __value);
	/**
	 * Bessel functions.<br>
	 * Original signature : <code>double j0(double)</code>
	 */
	extern double j0(double double1);
	/** Original signature : <code>double __j0(double)</code> */
	extern double __j0(double double1);
	/** Original signature : <code>double j1(double)</code> */
	extern double j1(double double1);
	/** Original signature : <code>double __j1(double)</code> */
	extern double __j1(double double1);
	/** Original signature : <code>double jn(int, double)</code> */
	extern double jn(int int1, double double1);
	/** Original signature : <code>double __jn(int, double)</code> */
	extern double __jn(int int1, double double1);
	/** Original signature : <code>double y0(double)</code> */
	extern double y0(double double1);
	/** Original signature : <code>double __y0(double)</code> */
	extern double __y0(double double1);
	/** Original signature : <code>double y1(double)</code> */
	extern double y1(double double1);
	/** Original signature : <code>double __y1(double)</code> */
	extern double __y1(double double1);
	/** Original signature : <code>double yn(int, double)</code> */
	extern double yn(int int1, double double1);
	/** Original signature : <code>double __yn(int, double)</code> */
	extern double __yn(int int1, double double1);
	/**
	 * Error and gamma functions.<br>
	 * Original signature : <code>double erf(double)</code>
	 */
	extern double erf(double double1);
	/** Original signature : <code>double __erf(double)</code> */
	extern double __erf(double double1);
	/** Original signature : <code>double erfc(double)</code> */
	extern double erfc(double double1);
	/** Original signature : <code>double __erfc(double)</code> */
	extern double __erfc(double double1);
	/** Original signature : <code>double lgamma(double)</code> */
	extern double lgamma(double double1);
	/** Original signature : <code>double __lgamma(double)</code> */
	extern double __lgamma(double double1);
	/**
	 * True gamma function.<br>
	 * Original signature : <code>double tgamma(double)</code>
	 */
	extern double tgamma(double double1);
	/** Original signature : <code>double __tgamma(double)</code> */
	extern double __tgamma(double double1);
	/**
	 * Obsolete alias for `lgamma'.<br>
	 * Original signature : <code>double gamma(double)</code>
	 */
	extern double gamma(double double1);
	/** Original signature : <code>double __gamma(double)</code> */
	extern double __gamma(double double1);
	/**
	 * Reentrant version of lgamma.  This function uses the global variable<br>
	 * `signgam'.  The reentrant version instead takes a pointer and stores<br>
	 * the value through it.<br>
	 * Original signature : <code>double lgamma_r(double, int*)</code>
	 */
	extern double lgamma_r(double double1, int* __signgamp);
	/** Original signature : <code>double __lgamma_r(double, int*)</code> */
	extern double __lgamma_r(double double1, int* __signgamp);
	/**
	 * Return the integer nearest X in the direction of the<br>
	 * prevailing rounding mode.<br>
	 * Original signature : <code>double rint(double)</code>
	 */
	extern double rint(double __x);
	/** Original signature : <code>double __rint(double)</code> */
	extern double __rint(double __x);
	/**
	 * Return X + epsilon if X < Y, X - epsilon if X > Y.<br>
	 * Original signature : <code>double nextafter(double, double)</code>
	 */
	extern double nextafter(double __x, double __y);
	/** Original signature : <code>double __nextafter(double, double)</code> */
	extern double __nextafter(double __x, double __y);
	/** Original signature : <code>double nexttoward(double, long double)</code> */
	extern double nexttoward(double __x, long double __y);
	/** Original signature : <code>double __nexttoward(double, long double)</code> */
	extern double __nexttoward(double __x, long double __y);
	/**
	 * Return the remainder of integer divison X / Y with infinite precision.<br>
	 * Original signature : <code>double remainder(double, double)</code>
	 */
	extern double remainder(double __x, double __y);
	/** Original signature : <code>double __remainder(double, double)</code> */
	extern double __remainder(double __x, double __y);
	/**
	 * Return X times (2 to the Nth power).<br>
	 * Original signature : <code>double scalbn(double, int)</code>
	 */
	extern double scalbn(double __x, int __n);
	/** Original signature : <code>double __scalbn(double, int)</code> */
	extern double __scalbn(double __x, int __n);
	/**
	 * Return the binary exponent of X, which must be nonzero.<br>
	 * Original signature : <code>int ilogb(double)</code>
	 */
	extern int ilogb(double __x);
	/** Original signature : <code>int __ilogb(double)</code> */
	extern int __ilogb(double __x);
	/**
	 * Return X times (2 to the Nth power).<br>
	 * Original signature : <code>double scalbln(double, long)</code>
	 */
	extern double scalbln(double __x, long __n);
	/** Original signature : <code>double __scalbln(double, long)</code> */
	extern double __scalbln(double __x, long __n);
	/**
	 * Round X to integral value in floating-point format using current<br>
	 * rounding direction, but do not raise inexact exception.<br>
	 * Original signature : <code>double nearbyint(double)</code>
	 */
	extern double nearbyint(double __x);
	/** Original signature : <code>double __nearbyint(double)</code> */
	extern double __nearbyint(double __x);
	/**
	 * Round X to nearest integral value, rounding halfway cases away from<br>
	 * zero.<br>
	 * Original signature : <code>double round(double)</code>
	 */
	extern double round(double __x);
	/** Original signature : <code>double __round(double)</code> */
	extern double __round(double __x);
	/**
	 * Round X to the integral value in floating-point format nearest but<br>
	 * not larger in magnitude.<br>
	 * Original signature : <code>double trunc(double)</code>
	 */
	extern double trunc(double __x);
	/** Original signature : <code>double __trunc(double)</code> */
	extern double __trunc(double __x);
	/**
	 * Compute remainder of X and Y and put in *QUO a value with sign of x/y<br>
	 * and magnitude congruent `mod 2^n' to the magnitude of the integral<br>
	 * quotient x/y, with n >= 3.<br>
	 * Original signature : <code>double remquo(double, double, int*)</code>
	 */
	extern double remquo(double __x, double __y, int* __quo);
	/** Original signature : <code>double __remquo(double, double, int*)</code> */
	extern double __remquo(double __x, double __y, int* __quo);
	/**
	 * Round X to nearest integral value according to current rounding<br>
	 * direction.<br>
	 * Original signature : <code>long lrint(double)</code>
	 */
	extern long lrint(double __x);
	/** Original signature : <code>long __lrint(double)</code> */
	extern long __lrint(double __x);
	/** Original signature : <code>long long llrint(double)</code> */
	extern long long llrint(double __x);
	/** Original signature : <code>long long __llrint(double)</code> */
	extern long long __llrint(double __x);
	/**
	 * Round X to nearest integral value, rounding halfway cases away from<br>
	 * zero.<br>
	 * Original signature : <code>long lround(double)</code>
	 */
	extern long lround(double __x);
	/** Original signature : <code>long __lround(double)</code> */
	extern long __lround(double __x);
	/** Original signature : <code>long long llround(double)</code> */
	extern long long llround(double __x);
	/** Original signature : <code>long long __llround(double)</code> */
	extern long long __llround(double __x);
	/**
	 * Return positive difference between X and Y.<br>
	 * Original signature : <code>double fdim(double, double)</code>
	 */
	extern double fdim(double __x, double __y);
	/** Original signature : <code>double __fdim(double, double)</code> */
	extern double __fdim(double __x, double __y);
	/**
	 * Return maximum numeric value from X and Y.<br>
	 * Original signature : <code>double fmax(double, double)</code>
	 */
	extern double fmax(double __x, double __y);
	/** Original signature : <code>double __fmax(double, double)</code> */
	extern double __fmax(double __x, double __y);
	/**
	 * Return minimum numeric value from X and Y.<br>
	 * Original signature : <code>double fmin(double, double)</code>
	 */
	extern double fmin(double __x, double __y);
	/** Original signature : <code>double __fmin(double, double)</code> */
	extern double __fmin(double __x, double __y);
	/**
	 * Classify given number.<br>
	 * Original signature : <code>int __fpclassify(double)</code>
	 */
	extern int __fpclassify(double __value);
	/**
	 * Test for negative number.<br>
	 * Original signature : <code>int __signbit(double)</code>
	 */
	extern int __signbit(double __value);
	/**
	 * Multiply-add function computed as a ternary operation.<br>
	 * Original signature : <code>double fma(double, double, double)</code>
	 */
	extern double fma(double __x, double __y, double __z);
	/** Original signature : <code>double __fma(double, double, double)</code> */
	extern double __fma(double __x, double __y, double __z);
	/**
	 * Return X times (2 to the Nth power).<br>
	 * Original signature : <code>double scalb(double, double)</code>
	 */
	extern double scalb(double __x, double __n);
	/** Original signature : <code>double __scalb(double, double)</code> */
	extern double __scalb(double __x, double __n);
	/** This variable is used by `gamma' and `lgamma'. */
	extern int signgam;
	/** All floating-point numbers can be put in one of these categories. */
	enum {
		FP_NAN = 0,
		FP_INFINITE = 1,
		FP_ZERO = 2,
		FP_SUBNORMAL = 3,
		FP_NORMAL = 4
	};
	/** Support for various different standard error handling behaviors. */
	typedef enum _LIB_VERSION_TYPE {
		_IEEE_ = -1 /* According to IEEE 754/IEEE 854.  */,
		_SVID_ /* According to System V, release 4.  */,
		_XOPEN_ /* Nowadays also Unix98.  */,
		_POSIX_,
		_ISOC_ /* Actually this is ISO C99.  */
	} _LIB_VERSION_TYPE;
	/**
	 * This variable can be changed at run-time to any of the values above to<br>
	 * affect floating point error handling behavior (it may also be necessary<br>
	 * to change the hardware FPU exception settings).
	 */
	extern _LIB_VERSION_TYPE _LIB_VERSION;
	struct __exception {
		int type;
		char* name;
		double arg1;
		double arg2;
		double retval;
	};
	/** Original signature : <code>int matherr(__exception*)</code> */
	extern int matherr(__exception* __exc) throw();
}
extern ""C"" {
/** Define outside of namespace so the C++ is happy. */
	struct _IO_FILE;
	/** The opaque type of streams.  This is the definition used elsewhere. */
	typedef _IO_FILE FILE;
	/** The opaque type of streams.  This is the definition used elsewhere. */
	typedef _IO_FILE __FILE;
	typedef unsigned int wint_t;
	/** Conversion state information. */
	typedef struct __mbstate_t {
		int __count;
		__value_union __value;
		union  __value_union {
			wint_t __wch;
			char[4] __wchb;
		};
	} __mbstate_t;
	typedef struct _G_fpos_t {
		__off_t __pos;
		__mbstate_t __state;
	} _G_fpos_t;
	typedef struct _G_fpos64_t {
		__off64_t __pos;
		__mbstate_t __state;
	} _G_fpos64_t;
	typedef __builtin_va_list __gnuc_va_list;
	struct _IO_jump_t;
	struct _IO_FILE;
	typedef void _IO_lock_t;
	struct _IO_marker {
		_IO_marker* _next;
		_IO_FILE* _sbuf;
		/** if _pos < 0, it points to _buf->eBptr()+_pos. FIXME comment */
		int _pos;
	};
	/** This is the structure from the libstdc++ codecvt class. */
	enum __codecvt_result {
		__codecvt_ok,
		__codecvt_partial,
		__codecvt_error,
		__codecvt_noconv
	};
	struct _IO_FILE {
		int _flags; /* High-order word is _IO_MAGIC; rest is flags. */
		/** Note:  Tk uses the _IO_read_ptr and _IO_read_end fields directly. */
		char* _IO_read_ptr; /* Current read pointer */
		char* _IO_read_end; /* End of get area. */
		char* _IO_read_base; /* Start of putback+get area. */
		char* _IO_write_base; /* Start of put area. */
		char* _IO_write_ptr; /* Current put pointer. */
		char* _IO_write_end; /* End of put area. */
		char* _IO_buf_base; /* Start of reserve area. */
		char* _IO_buf_end; /* End of reserve area. */
		/** The following fields are used to support backing up and undo. */
		char* _IO_save_base; /* Pointer to start of non-current get area. */
		char* _IO_backup_base; /* Pointer to first valid character of backup area */
		char* _IO_save_end; /* Pointer to end of non-current get area. */
		_IO_marker* _markers;
		_IO_FILE* _chain;
		int _fileno;
		int _flags2;
		__off_t _old_offset; /* This used to be _offset but it's too small.  */
		/** 1+column number of pbase(); 0 is unknown. */
		unsigned short _cur_column;
		signed char _vtable_offset;
		char[1] _shortbuf;
		_IO_lock_t* _lock;
		__off64_t _offset;
		void* __pad1;
		void* __pad2;
		void* __pad3;
		void* __pad4;
		size_t __pad5;
		int _mode;
		/** Make sure we don't get into trouble again. */
		char[15 * sizeof(int) - 4 * sizeof(void*) - sizeof(size_t)] _unused2;
	};
	struct _IO_FILE_plus;
	extern _IO_FILE_plus _IO_2_1_stdin_;
	extern _IO_FILE_plus _IO_2_1_stdout_;
	extern _IO_FILE_plus _IO_2_1_stderr_;
	/**
	 * Read NBYTES bytes from COOKIE into a buffer pointed to by BUF.<br>
	 * Return number of bytes read.
	 */
	typedef __ssize_t __io_read_fn(void* __cookie, char* __buf, size_t __nbytes);
	/**
	 * Write N bytes pointed to by BUF to COOKIE.  Write all N bytes<br>
	 * unless there is an error.  Return number of bytes written.  If<br>
	 * there is an error, return 0 and do not write anything.  If the file<br>
	 * has been opened for append (__mode.__append set), then set the file<br>
	 * pointer to the end of the file and then do the write; if not, just<br>
	 * write at the current file pointer.
	 */
	typedef __ssize_t __io_write_fn(void* __cookie, const char* __buf, size_t __n);
	/**
	 * Move COOKIE's file position to *POS bytes from the<br>
	 * beginning of the file (if W is SEEK_SET),<br>
	 * the current position (if W is SEEK_CUR),<br>
	 * or the end of the file (if W is SEEK_END).<br>
	 * Set *POS to the new file position.<br>
	 * Returns zero if successful, nonzero if not.
	 */
	typedef int __io_seek_fn(void* __cookie, __off64_t* __pos, int __w);
	/** Close COOKIE. */
	typedef int __io_close_fn(void* __cookie);
	extern ""C"" {
/** Original signature : <code>int __underflow(_IO_FILE*)</code> */
		extern int __underflow(_IO_FILE* _IO_FILEPtr1);
		/** Original signature : <code>int __uflow(_IO_FILE*)</code> */
		extern int __uflow(_IO_FILE* _IO_FILEPtr1);
		/** Original signature : <code>int __overflow(_IO_FILE*, int)</code> */
		extern int __overflow(_IO_FILE* _IO_FILEPtr1, int int1);
		/** Original signature : <code>int _IO_getc(_IO_FILE*)</code> */
		extern int _IO_getc(_IO_FILE* __fp);
		/** Original signature : <code>int _IO_putc(int, _IO_FILE*)</code> */
		extern int _IO_putc(int __c, _IO_FILE* __fp);
		/** Original signature : <code>int _IO_feof(_IO_FILE*)</code> */
		extern int _IO_feof(_IO_FILE* __fp);
		/** Original signature : <code>int _IO_ferror(_IO_FILE*)</code> */
		extern int _IO_ferror(_IO_FILE* __fp);
		/** Original signature : <code>int _IO_peekc_locked(_IO_FILE*)</code> */
		extern int _IO_peekc_locked(_IO_FILE* __fp);
		/** Original signature : <code>void _IO_flockfile(_IO_FILE*)</code> */
		extern void _IO_flockfile(_IO_FILE* _IO_FILEPtr1);
		/** Original signature : <code>void _IO_funlockfile(_IO_FILE*)</code> */
		extern void _IO_funlockfile(_IO_FILE* _IO_FILEPtr1);
		/** Original signature : <code>int _IO_ftrylockfile(_IO_FILE*)</code> */
		extern int _IO_ftrylockfile(_IO_FILE* _IO_FILEPtr1);
		/** Original signature : <code>int _IO_vfscanf(_IO_FILE*, const char*, __gnuc_va_list, int*)</code> */
		extern int _IO_vfscanf(_IO_FILE* _IO_FILEPtr1, const char* charPtr1, __gnuc_va_list __gnuc_va_list1, int* intPtr1);
		/** Original signature : <code>int _IO_vfprintf(_IO_FILE*, const char*, __gnuc_va_list)</code> */
		extern int _IO_vfprintf(_IO_FILE* _IO_FILEPtr1, const char* charPtr1, __gnuc_va_list __gnuc_va_list1);
		/** Original signature : <code>__ssize_t _IO_padn(_IO_FILE*, int, __ssize_t)</code> */
		extern __ssize_t _IO_padn(_IO_FILE* _IO_FILEPtr1, int int1, __ssize_t __ssize_t1);
		/** Original signature : <code>size_t _IO_sgetn(_IO_FILE*, void*, size_t)</code> */
		extern size_t _IO_sgetn(_IO_FILE* _IO_FILEPtr1, void* voidPtr1, size_t size_t1);
		/** Original signature : <code>__off64_t _IO_seekoff(_IO_FILE*, __off64_t, int, int)</code> */
		extern __off64_t _IO_seekoff(_IO_FILE* _IO_FILEPtr1, __off64_t __off64_t1, int int1, int int2);
		/** Original signature : <code>__off64_t _IO_seekpos(_IO_FILE*, __off64_t, int)</code> */
		extern __off64_t _IO_seekpos(_IO_FILE* _IO_FILEPtr1, __off64_t __off64_t1, int int1);
		/** Original signature : <code>void _IO_free_backup_area(_IO_FILE*)</code> */
		extern void _IO_free_backup_area(_IO_FILE* _IO_FILEPtr1);
	}
	typedef __gnuc_va_list va_list;
	typedef _G_fpos_t fpos_t;
	/** Standard streams. */
	extern _IO_FILE* stdin; /* Standard input stream.  */
	extern _IO_FILE* stdout; /* Standard output stream.  */
	extern _IO_FILE* stderr; /* Standard error output stream.  */
	/**
	 * Remove file FILENAME.<br>
	 * Original signature : <code>int remove(const char*)</code>
	 */
	extern int remove(const char* __filename);
	/**
	 * Rename file OLD to NEW.<br>
	 * Original signature : <code>int rename(const char*, const char*)</code>
	 */
	extern int rename(const char* __old, const char* __new);
	/**
	 * Rename file OLD relative to OLDFD to NEW relative to NEWFD.<br>
	 * Original signature : <code>int renameat(int, const char*, int, const char*)</code>
	 */
	extern int renameat(int __oldfd, const char* __old, int __newfd, const char* __new);
	/** Original signature : <code>FILE* tmpfile()</code> */
	extern FILE* tmpfile();
	/**
	 * Generate a temporary filename.<br>
	 * Original signature : <code>char* tmpnam(char*)</code>
	 */
	extern char* tmpnam(char* __s);
	/**
	 * This is the reentrant variant of `tmpnam'.  The only difference is<br>
	 * that it does not allow S to be NULL.<br>
	 * Original signature : <code>char* tmpnam_r(char*)</code>
	 */
	extern char* tmpnam_r(char* __s);
	/**
	 * Generate a unique temporary filename using up to five characters of PFX<br>
	 * if it is not NULL.  The directory to put this file in is searched for<br>
	 * as follows: First the environment variable "TMPDIR" is checked.<br>
	 * If it contains the name of a writable directory, that directory is used.<br>
	 * If not and if DIR is not NULL, that value is checked.  If that fails,<br>
	 * P_tmpdir is tried and finally "/tmp".  The storage for the filename<br>
	 * is allocated by `malloc'.<br>
	 * Original signature : <code>char* tempnam(const char*, const char*)</code>
	 */
	extern char* tempnam(const char* __dir, const char* __pfx);
	/**
	 * Close STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int fclose(FILE*)</code>
	 */
	extern int fclose(FILE* __stream);
	/**
	 * Flush STREAM, or all streams if STREAM is NULL.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int fflush(FILE*)</code>
	 */
	extern int fflush(FILE* __stream);
	/**
	 * Faster versions when locking is not required.<br>
	 * This function is not part of POSIX and therefore no official<br>
	 * cancellation point.  But due to similarity with an POSIX interface<br>
	 * or due to the implementation it is a cancellation point and<br>
	 * therefore not marked with __THROW.<br>
	 * Original signature : <code>int fflush_unlocked(FILE*)</code>
	 */
	extern int fflush_unlocked(FILE* __stream);
	/**
	 * Open a file and create a new stream for it.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>FILE* fopen(const char*, const char*)</code>
	 */
	extern FILE* fopen(const char* __filename, const char* __modes);
	/**
	 * Open a file, replacing an existing stream with it.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>FILE* freopen(const char*, const char*, FILE*)</code>
	 */
	extern FILE* freopen(const char* __filename, const char* __modes, FILE* __stream);
	/**
	 * Create a new stream that refers to an existing system file descriptor.<br>
	 * Original signature : <code>FILE* fdopen(int, const char*)</code>
	 */
	extern FILE* fdopen(int __fd, const char* __modes);
	/**
	 * Create a new stream that refers to a memory buffer.<br>
	 * Original signature : <code>FILE* fmemopen(void*, size_t, const char*)</code>
	 */
	extern FILE* fmemopen(void* __s, size_t __len, const char* __modes);
	/**
	 * Open a stream that writes into a malloc'd buffer that is expanded as<br>
	 * necessary.  *BUFLOC and *SIZELOC are updated with the buffer's location<br>
	 * and the number of characters written on fflush or fclose.<br>
	 * Original signature : <code>FILE* open_memstream(char**, size_t*)</code>
	 */
	extern FILE* open_memstream(char** __bufloc, size_t* __sizeloc);
	/**
	 * If BUF is NULL, make STREAM unbuffered.<br>
	 * Else make it use buffer BUF, of size BUFSIZ.<br>
	 * Original signature : <code>void setbuf(FILE*, char*)</code>
	 */
	extern void setbuf(FILE* __stream, char* __buf);
	/**
	 * Make STREAM use buffering mode MODE.<br>
	 * If BUF is not NULL, use N bytes of it for buffering;<br>
	 * else allocate an internal buffer N bytes long.<br>
	 * Original signature : <code>int setvbuf(FILE*, char*, int, size_t)</code>
	 */
	extern int setvbuf(FILE* __stream, char* __buf, int __modes, size_t __n);
	/**
	 * If BUF is NULL, make STREAM unbuffered.<br>
	 * Else make it use SIZE bytes of BUF for buffering.<br>
	 * Original signature : <code>void setbuffer(FILE*, char*, size_t)</code>
	 */
	extern void setbuffer(FILE* __stream, char* __buf, size_t __size);
	/**
	 * Make STREAM line-buffered.<br>
	 * Original signature : <code>void setlinebuf(FILE*)</code>
	 */
	extern void setlinebuf(FILE* __stream);
	/**
	 * Write formatted output to STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int fprintf(FILE*, const char*, null)</code>
	 */
	extern int fprintf(FILE* __stream, const char* __format, ...);
	/**
	 * Write formatted output to stdout.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int printf(const char*, null)</code>
	 */
	extern int printf(const char* __format, ...);
	/**
	 * Write formatted output to S.<br>
	 * Original signature : <code>int sprintf(char*, const char*, null)</code>
	 */
	extern int sprintf(char* __s, const char* __format, ...);
	/**
	 * Write formatted output to S from argument list ARG.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int vfprintf(FILE*, const char*, __gnuc_va_list)</code>
	 */
	extern int vfprintf(FILE* __s, const char* __format, __gnuc_va_list __arg);
	/**
	 * Write formatted output to stdout from argument list ARG.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int vprintf(const char*, __gnuc_va_list)</code>
	 */
	extern int vprintf(const char* __format, __gnuc_va_list __arg);
	/**
	 * Write formatted output to S from argument list ARG.<br>
	 * Original signature : <code>int vsprintf(char*, const char*, __gnuc_va_list)</code>
	 */
	extern int vsprintf(char* __s, const char* __format, __gnuc_va_list __arg);
	/**
	 * Maximum chars of output to write in MAXLEN.<br>
	 * Original signature : <code>int snprintf(char*, size_t, const char*, null)</code>
	 */
	extern int snprintf(char* __s, size_t __maxlen, const char* __format, ...);
	/** Original signature : <code>int vsnprintf(char*, size_t, const char*, __gnuc_va_list)</code> */
	extern int vsnprintf(char* __s, size_t __maxlen, const char* __format, __gnuc_va_list __arg);
	/**
	 * Write formatted output to a file descriptor.<br>
	 * Original signature : <code>int vdprintf(int, const char*, __gnuc_va_list)</code>
	 */
	extern int vdprintf(int __fd, const char* __fmt, __gnuc_va_list __arg);
	/** Original signature : <code>int dprintf(int, const char*, null)</code> */
	extern int dprintf(int __fd, const char* __fmt, ...);
	/**
	 * Read formatted input from STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int fscanf(FILE*, const char*, null)</code>
	 */
	extern int fscanf(FILE* __stream, const char* __format, ...);
	/**
	 * Read formatted input from stdin.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int scanf(const char*, null)</code>
	 */
	extern int scanf(const char* __format, ...);
	/**
	 * Read formatted input from S.<br>
	 * Original signature : <code>int sscanf(const char*, const char*, null)</code>
	 */
	extern int sscanf(const char* __s, const char* __format, ...);
	/** Original signature : <code>int __isoc99_fscanf(FILE*, const char*, null)</code> */
	extern int __isoc99_fscanf(FILE* __stream, const char* __format, ...);
	/** Original signature : <code>int __isoc99_scanf(const char*, null)</code> */
	extern int __isoc99_scanf(const char* __format, ...);
	/** Original signature : <code>int __isoc99_sscanf(const char*, const char*, null)</code> */
	extern int __isoc99_sscanf(const char* __s, const char* __format, ...);
	/**
	 * Read formatted input from S into argument list ARG.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int vfscanf(FILE*, const char*, __gnuc_va_list)</code>
	 */
	extern int vfscanf(FILE* __s, const char* __format, __gnuc_va_list __arg);
	/**
	 * Read formatted input from stdin into argument list ARG.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int vscanf(const char*, __gnuc_va_list)</code>
	 */
	extern int vscanf(const char* __format, __gnuc_va_list __arg);
	/**
	 * Read formatted input from S into argument list ARG.<br>
	 * Original signature : <code>int vsscanf(const char*, const char*, __gnuc_va_list)</code>
	 */
	extern int vsscanf(const char* __s, const char* __format, __gnuc_va_list __arg);
	/** Original signature : <code>int __isoc99_vfscanf(FILE*, const char*, __gnuc_va_list)</code> */
	extern int __isoc99_vfscanf(FILE* __s, const char* __format, __gnuc_va_list __arg);
	/** Original signature : <code>int __isoc99_vscanf(const char*, __gnuc_va_list)</code> */
	extern int __isoc99_vscanf(const char* __format, __gnuc_va_list __arg);
	/** Original signature : <code>int __isoc99_vsscanf(const char*, const char*, __gnuc_va_list)</code> */
	extern int __isoc99_vsscanf(const char* __s, const char* __format, __gnuc_va_list __arg);
	/**
	 * Read a character from STREAM.<br>
	 * These functions are possible cancellation points and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int fgetc(FILE*)</code>
	 */
	extern int fgetc(FILE* __stream);
	/** Original signature : <code>int getc(FILE*)</code> */
	extern int getc(FILE* __stream);
	/**
	 * Read a character from stdin.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int getchar()</code>
	 */
	extern int getchar();
	/**
	 * These are defined in POSIX.1:1996.<br>
	 * These functions are possible cancellation points and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int getc_unlocked(FILE*)</code>
	 */
	extern int getc_unlocked(FILE* __stream);
	/** Original signature : <code>int getchar_unlocked()</code> */
	extern int getchar_unlocked();
	/**
	 * Faster version when locking is not necessary.<br>
	 * This function is not part of POSIX and therefore no official<br>
	 * cancellation point.  But due to similarity with an POSIX interface<br>
	 * or due to the implementation it is a cancellation point and<br>
	 * therefore not marked with __THROW.<br>
	 * Original signature : <code>int fgetc_unlocked(FILE*)</code>
	 */
	extern int fgetc_unlocked(FILE* __stream);
	/**
	 * Write a character to STREAM.<br>
	 * These functions are possible cancellation points and therefore not<br>
	 * marked with __THROW.<br>
	 * These functions is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int fputc(int, FILE*)</code>
	 */
	extern int fputc(int __c, FILE* __stream);
	/** Original signature : <code>int putc(int, FILE*)</code> */
	extern int putc(int __c, FILE* __stream);
	/**
	 * Write a character to stdout.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int putchar(int)</code>
	 */
	extern int putchar(int __c);
	/**
	 * Faster version when locking is not necessary.<br>
	 * This function is not part of POSIX and therefore no official<br>
	 * cancellation point.  But due to similarity with an POSIX interface<br>
	 * or due to the implementation it is a cancellation point and<br>
	 * therefore not marked with __THROW.<br>
	 * Original signature : <code>int fputc_unlocked(int, FILE*)</code>
	 */
	extern int fputc_unlocked(int __c, FILE* __stream);
	/**
	 * These are defined in POSIX.1:1996.<br>
	 * These functions are possible cancellation points and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int putc_unlocked(int, FILE*)</code>
	 */
	extern int putc_unlocked(int __c, FILE* __stream);
	/** Original signature : <code>int putchar_unlocked(int)</code> */
	extern int putchar_unlocked(int __c);
	/**
	 * Get a word (int) from STREAM.<br>
	 * Original signature : <code>int getw(FILE*)</code>
	 */
	extern int getw(FILE* __stream);
	/**
	 * Write a word (int) to STREAM.<br>
	 * Original signature : <code>int putw(int, FILE*)</code>
	 */
	extern int putw(int __w, FILE* __stream);
	/**
	 * Get a newline-terminated string of finite length from STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>char* fgets(char*, int, FILE*)</code>
	 */
	extern char* fgets(char* __s, int __n, FILE* __stream);
	/**
	 * Get a newline-terminated string from stdin, removing the newline.<br>
	 * DO NOT USE THIS FUNCTION!!  There is no limit on how much it will read.<br>
	 * The function has been officially removed in ISO C11.  This opportunity<br>
	 * is used to also remove it from the GNU feature list.  It is now only<br>
	 * available when explicitly using an old ISO C, Unix, or POSIX standard.<br>
	 * GCC defines _GNU_SOURCE when building C++ code and the function is still<br>
	 * in C++11, so it is also available for C++.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>char* gets(char*)</code>
	 */
	extern char* gets(char* __s);
	/**
	 * Read up to (and including) a DELIMITER from STREAM into *LINEPTR<br>
	 * (and null-terminate it). *LINEPTR is a pointer returned from malloc (or<br>
	 * NULL), pointing to *N characters of space.  It is realloc'd as<br>
	 * necessary.  Returns the number of characters read (not including the<br>
	 * null terminator), or -1 on error or EOF.<br>
	 * These functions are not part of POSIX and therefore no official<br>
	 * cancellation point.  But due to similarity with an POSIX interface<br>
	 * or due to the implementation they are cancellation points and<br>
	 * therefore not marked with __THROW.<br>
	 * Original signature : <code>__ssize_t __getdelim(char**, size_t*, int, FILE*)</code>
	 */
	extern __ssize_t __getdelim(char** __lineptr, size_t* __n, int __delimiter, FILE* __stream);
	/** Original signature : <code>__ssize_t getdelim(char**, size_t*, int, FILE*)</code> */
	extern __ssize_t getdelim(char** __lineptr, size_t* __n, int __delimiter, FILE* __stream);
	/**
	 * Like `getdelim', but reads up to a newline.<br>
	 * This function is not part of POSIX and therefore no official<br>
	 * cancellation point.  But due to similarity with an POSIX interface<br>
	 * or due to the implementation it is a cancellation point and<br>
	 * therefore not marked with __THROW.<br>
	 * Original signature : <code>__ssize_t getline(char**, size_t*, FILE*)</code>
	 */
	extern __ssize_t getline(char** __lineptr, size_t* __n, FILE* __stream);
	/**
	 * Write a string to STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int fputs(const char*, FILE*)</code>
	 */
	extern int fputs(const char* __s, FILE* __stream);
	/**
	 * Write a string, followed by a newline, to stdout.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int puts(const char*)</code>
	 */
	extern int puts(const char* __s);
	/**
	 * Push a character back onto the input buffer of STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int ungetc(int, FILE*)</code>
	 */
	extern int ungetc(int __c, FILE* __stream);
	/**
	 * Read chunks of generic data from STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>size_t fread(void*, size_t, size_t, FILE*)</code>
	 */
	extern size_t fread(void* __ptr, size_t __size, size_t __n, FILE* __stream);
	/**
	 * Write chunks of generic data to STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>size_t fwrite(const void*, size_t, size_t, FILE*)</code>
	 */
	extern size_t fwrite(const void* __ptr, size_t __size, size_t __n, FILE* __s);
	/**
	 * Faster versions when locking is not necessary.<br>
	 * These functions are not part of POSIX and therefore no official<br>
	 * cancellation point.  But due to similarity with an POSIX interface<br>
	 * or due to the implementation they are cancellation points and<br>
	 * therefore not marked with __THROW.<br>
	 * Original signature : <code>size_t fread_unlocked(void*, size_t, size_t, FILE*)</code>
	 */
	extern size_t fread_unlocked(void* __ptr, size_t __size, size_t __n, FILE* __stream);
	/** Original signature : <code>size_t fwrite_unlocked(const void*, size_t, size_t, FILE*)</code> */
	extern size_t fwrite_unlocked(const void* __ptr, size_t __size, size_t __n, FILE* __stream);
	/**
	 * Seek to a certain position on STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int fseek(FILE*, long, int)</code>
	 */
	extern int fseek(FILE* __stream, long __off, int __whence);
	/**
	 * Return the current position of STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>long ftell(FILE*)</code>
	 */
	extern long ftell(FILE* __stream);
	/**
	 * Rewind to the beginning of STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>void rewind(FILE*)</code>
	 */
	extern void rewind(FILE* __stream);
	/**
	 * Seek to a certain position on STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int fseeko(FILE*, __off_t, int)</code>
	 */
	extern int fseeko(FILE* __stream, __off_t __off, int __whence);
	/**
	 * Return the current position of STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>__off_t ftello(FILE*)</code>
	 */
	extern __off_t ftello(FILE* __stream);
	/**
	 * Get STREAM's position.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int fgetpos(FILE*, fpos_t*)</code>
	 */
	extern int fgetpos(FILE* __stream, fpos_t* __pos);
	/**
	 * Set STREAM's position.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int fsetpos(FILE*, const fpos_t*)</code>
	 */
	extern int fsetpos(FILE* __stream, const fpos_t* __pos);
	/**
	 * Clear the error and EOF indicators for STREAM.<br>
	 * Original signature : <code>void clearerr(FILE*)</code>
	 */
	extern void clearerr(FILE* __stream);
	/**
	 * Return the EOF indicator for STREAM.<br>
	 * Original signature : <code>int feof(FILE*)</code>
	 */
	extern int feof(FILE* __stream);
	/**
	 * Return the error indicator for STREAM.<br>
	 * Original signature : <code>int ferror(FILE*)</code>
	 */
	extern int ferror(FILE* __stream);
	/**
	 * Faster versions when locking is not required.<br>
	 * Original signature : <code>void clearerr_unlocked(FILE*)</code>
	 */
	extern void clearerr_unlocked(FILE* __stream);
	/** Original signature : <code>int feof_unlocked(FILE*)</code> */
	extern int feof_unlocked(FILE* __stream);
	/** Original signature : <code>int ferror_unlocked(FILE*)</code> */
	extern int ferror_unlocked(FILE* __stream);
	/**
	 * Print a message describing the meaning of the value of errno.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>void perror(const char*)</code>
	 */
	extern void perror(const char* __s);
	extern int sys_nerr;
	extern const char*[] sys_errlist;
	/**
	 * Return the system file descriptor for STREAM.<br>
	 * Original signature : <code>int fileno(FILE*)</code>
	 */
	extern int fileno(FILE* __stream);
	/**
	 * Faster version when locking is not required.<br>
	 * Original signature : <code>int fileno_unlocked(FILE*)</code>
	 */
	extern int fileno_unlocked(FILE* __stream);
	/**
	 * Create a new stream connected to a pipe running the given command.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>FILE* popen(const char*, const char*)</code>
	 */
	extern FILE* popen(const char* __command, const char* __modes);
	/**
	 * Close a stream opened by popen and return the status of its child.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.<br>
	 * Original signature : <code>int pclose(FILE*)</code>
	 */
	extern int pclose(FILE* __stream);
	/**
	 * Return the name of the controlling terminal.<br>
	 * Original signature : <code>char* ctermid(char*)</code>
	 */
	extern char* ctermid(char* __s);
	/**
	 * Acquire ownership of STREAM.<br>
	 * Original signature : <code>void flockfile(FILE*)</code>
	 */
	extern void flockfile(FILE* __stream);
	/**
	 * Try to acquire ownership of STREAM but do not block if it is not<br>
	 * possible.<br>
	 * Original signature : <code>int ftrylockfile(FILE*)</code>
	 */
	extern int ftrylockfile(FILE* __stream);
	/**
	 * Relinquish the ownership granted for STREAM.<br>
	 * Original signature : <code>void funlockfile(FILE*)</code>
	 */
	extern void funlockfile(FILE* __stream);
}
/** ------------------------------------------------------------------------- */
typedef _lprec lprec;
typedef _INVrec INVrec;
union  QSORTrec;
/** B4 factorization optimization data */
typedef struct B4rec {
	int* B4_var; /* Position of basic columns in the B4 basis */
	int* var_B4; /* Variable in the B4 basis */
	int* B4_row; /* B4 position of the i'th row */
	int* row_B4; /* Original position of the i'th row */
	double* wcol;
	int* nzwcol;
} _B4rec;
typedef struct OBJmonrec {
	lprec* lp;
	int oldpivstrategy;
	int oldpivrule;
	int pivrule;
	int ruleswitches;
	int[2] limitstall;
	int limitruleswitches;
	int[5] idxstep;
	int countstep;
	int startstep;
	int currentstep;
	int Rcycle;
	int Ccycle;
	int Ncycle;
	int Mcycle;
	int Icount;
	double thisobj;
	double prevobj;
	double[5] objstep;
	double thisinfeas;
	double previnfeas;
	double epsvalue;
	char[10] spxfunc;
	unsigned char pivdynamic;
	unsigned char isdual;
	unsigned char active;
} _OBJmonrec;
typedef struct edgerec {
	double* edgeVector;
} _edgerec;
typedef struct pricerec {
	double theta;
	double pivot;
	double epspivot;
	int varno;
	lprec* lp;
	unsigned char isdual;
} _pricerec;
typedef struct multirec {
	lprec* lp;
	int size; /* The maximum number of multiply priced rows/columns */
	int used; /* The current / active number of multiply priced rows/columns */
	int limit; /* The active/used count at which a full update is triggered */
	pricerec* items; /* Array of best multiply priced rows/columns */
	int* freeList; /* The indeces of available positions in "items" */
	QSORTrec* sortedList; /* List of pointers to "pricerec" items in sorted order */
	double* stepList; /* Working array (values in sortedList order) */
	double* valueList; /* Working array (values in sortedList order) */
	int* indexSet; /* The final exported index list of pivot variables */
	int active; /* Index of currently active multiply priced row/column */
	int retries;
	double step_base;
	double step_last;
	double obj_base;
	double obj_last;
	double epszero;
	double maxpivot;
	double maxbound;
	unsigned char sorted;
	unsigned char truncinf;
	unsigned char objcheck;
	unsigned char dirty;
} _multirec;
/** Temporary data storage arrays */
typedef struct workarraysrec {
	lprec* lp;
	int size;
	int count;
	char** vectorarray;
	int* vectorsize;
} _workarraysrec;
typedef struct LLrec {
	int size; /* The allocated list size */
	int count; /* The current entry count */
	int firstitem;
	int lastitem;
	int* map; /* The list of forward and backward-mapped entries */
} _LLrec;
typedef struct PVrec {
	int count; /* The allocated list item count */
	int* startpos; /* Starting index of the current value */
	double* value; /* The list of forward and backward-mapped entries */
	_PVrec* parent; /* The parent record in a pushed chain */
} _PVrec;
extern ""C"" {
/**
	 * Put function headers here<br>
	 * Original signature : <code>char allocCHAR(lprec*, char**, int, unsigned char)</code>
	 */
	unsigned char allocCHAR(lprec* lp, char** ptr, int size, unsigned char clear);
	/** Original signature : <code>char allocMYBOOL(lprec*, unsigned char**, int, unsigned char)</code> */
	unsigned char allocMYBOOL(lprec* lp, unsigned char** ptr, int size, unsigned char clear);
	/** Original signature : <code>char allocINT(lprec*, int**, int, unsigned char)</code> */
	unsigned char allocINT(lprec* lp, int** ptr, int size, unsigned char clear);
	/** Original signature : <code>char allocREAL(lprec*, double**, int, unsigned char)</code> */
	unsigned char allocREAL(lprec* lp, double** ptr, int size, unsigned char clear);
	/** Original signature : <code>char allocLREAL(lprec*, double**, int, unsigned char)</code> */
	unsigned char allocLREAL(lprec* lp, double** ptr, int size, unsigned char clear);
	/** Original signature : <code>char allocFREE(lprec*, void**)</code> */
	unsigned char allocFREE(lprec* lp, void** ptr);
	/** Original signature : <code>double* cloneREAL(lprec*, double*, int)</code> */
	double* cloneREAL(lprec* lp, double* origlist, int size);
	/** Original signature : <code>char* cloneMYBOOL(lprec*, unsigned char*, int)</code> */
	unsigned char* cloneMYBOOL(lprec* lp, unsigned char* origlist, int size);
	/** Original signature : <code>int* cloneINT(lprec*, int*, int)</code> */
	int* cloneINT(lprec* lp, int* origlist, int size);
	/** Original signature : <code>int comp_bits(unsigned char*, unsigned char*, int)</code> */
	int comp_bits(unsigned char* bitarray1, unsigned char* bitarray2, int items);
	/** Original signature : <code>workarraysrec* mempool_create(lprec*)</code> */
	workarraysrec* mempool_create(lprec* lp);
	/** Original signature : <code>char* mempool_obtainVector(workarraysrec*, int, int)</code> */
	char* mempool_obtainVector(workarraysrec* mempool, int count, int unitsize);
	/** Original signature : <code>char mempool_releaseVector(workarraysrec*, char*, unsigned char)</code> */
	unsigned char mempool_releaseVector(workarraysrec* mempool, char* memvector, unsigned char forcefree);
	/** Original signature : <code>char mempool_free(workarraysrec**)</code> */
	unsigned char mempool_free(workarraysrec** mempool);
	/** Original signature : <code>void roundVector(double*, int, double)</code> */
	void roundVector(double* myvector, int endpos, double roundzero);
	/** Original signature : <code>double normalizeVector(double*, int)</code> */
	double normalizeVector(double* myvector, int endpos);
	/** Original signature : <code>void swapINT(int*, int*)</code> */
	void swapINT(int* item1, int* item2);
	/** Original signature : <code>void swapREAL(double*, double*)</code> */
	void swapREAL(double* item1, double* item2);
	/** Original signature : <code>void swapPTR(void**, void**)</code> */
	void swapPTR(void** item1, void** item2);
	/** Original signature : <code>double restoreINT(double, double)</code> */
	double restoreINT(double valREAL, double epsilon);
	/** Original signature : <code>double roundToPrecision(double, double)</code> */
	double roundToPrecision(double value, double precision);
	/** Original signature : <code>int searchFor(int, int*, int, int, unsigned char)</code> */
	int searchFor(int target, int* attributes, int size, int offset, unsigned char absolute);
	/** Original signature : <code>char isINT(lprec*, double)</code> */
	unsigned char isINT(lprec* lp, double value);
	/** Original signature : <code>char isOrigFixed(lprec*, int)</code> */
	unsigned char isOrigFixed(lprec* lp, int varno);
	/** Original signature : <code>void chsign_bounds(double*, double*)</code> */
	void chsign_bounds(double* lobound, double* upbound);
	/** Original signature : <code>double rand_uniform(lprec*, double)</code> */
	double rand_uniform(lprec* lp, double range);
	/**
	 * Doubly linked list routines<br>
	 * Original signature : <code>int createLink(int, LLrec**, unsigned char*)</code>
	 */
	int createLink(int size, LLrec** linkmap, unsigned char* usedpos);
	/** Original signature : <code>char freeLink(LLrec**)</code> */
	unsigned char freeLink(LLrec** linkmap);
	/** Original signature : <code>int sizeLink(LLrec*)</code> */
	int sizeLink(LLrec* linkmap);
	/** Original signature : <code>char isActiveLink(LLrec*, int)</code> */
	unsigned char isActiveLink(LLrec* linkmap, int itemnr);
	/** Original signature : <code>int countActiveLink(LLrec*)</code> */
	int countActiveLink(LLrec* linkmap);
	/** Original signature : <code>int countInactiveLink(LLrec*)</code> */
	int countInactiveLink(LLrec* linkmap);
	/** Original signature : <code>int firstActiveLink(LLrec*)</code> */
	int firstActiveLink(LLrec* linkmap);
	/** Original signature : <code>int lastActiveLink(LLrec*)</code> */
	int lastActiveLink(LLrec* linkmap);
	/** Original signature : <code>char appendLink(LLrec*, int)</code> */
	unsigned char appendLink(LLrec* linkmap, int newitem);
	/** Original signature : <code>char insertLink(LLrec*, int, int)</code> */
	unsigned char insertLink(LLrec* linkmap, int afteritem, int newitem);
	/** Original signature : <code>char setLink(LLrec*, int)</code> */
	unsigned char setLink(LLrec* linkmap, int newitem);
	/** Original signature : <code>char fillLink(LLrec*)</code> */
	unsigned char fillLink(LLrec* linkmap);
	/** Original signature : <code>int nextActiveLink(LLrec*, int)</code> */
	int nextActiveLink(LLrec* linkmap, int backitemnr);
	/** Original signature : <code>int prevActiveLink(LLrec*, int)</code> */
	int prevActiveLink(LLrec* linkmap, int forwitemnr);
	/** Original signature : <code>int firstInactiveLink(LLrec*)</code> */
	int firstInactiveLink(LLrec* linkmap);
	/** Original signature : <code>int lastInactiveLink(LLrec*)</code> */
	int lastInactiveLink(LLrec* linkmap);
	/** Original signature : <code>int nextInactiveLink(LLrec*, int)</code> */
	int nextInactiveLink(LLrec* linkmap, int backitemnr);
	/** Original signature : <code>int prevInactiveLink(LLrec*, int)</code> */
	int prevInactiveLink(LLrec* linkmap, int forwitemnr);
	/** Original signature : <code>int removeLink(LLrec*, int)</code> */
	int removeLink(LLrec* linkmap, int itemnr);
	/** Original signature : <code>LLrec* cloneLink(LLrec*, int, unsigned char)</code> */
	LLrec* cloneLink(LLrec* sourcemap, int newsize, unsigned char freesource);
	/** Original signature : <code>int compareLink(LLrec*, LLrec*)</code> */
	int compareLink(LLrec* linkmap1, LLrec* linkmap2);
	/** Original signature : <code>char verifyLink(LLrec*, int, unsigned char)</code> */
	unsigned char verifyLink(LLrec* linkmap, int itemnr, unsigned char doappend);
	/**
	 * Packed vector routines<br>
	 * Original signature : <code>PVrec* createPackedVector(int, double*, int*)</code>
	 */
	PVrec* createPackedVector(int size, double* values, int* workvector);
	/** Original signature : <code>void pushPackedVector(PVrec*, PVrec*)</code> */
	void pushPackedVector(PVrec* PV, PVrec* parent);
	/** Original signature : <code>char unpackPackedVector(PVrec*, double**)</code> */
	unsigned char unpackPackedVector(PVrec* PV, double** target);
	/** Original signature : <code>double getvaluePackedVector(PVrec*, int)</code> */
	double getvaluePackedVector(PVrec* PV, int index);
	/** Original signature : <code>PVrec* popPackedVector(PVrec*)</code> */
	PVrec* popPackedVector(PVrec* PV);
	/** Original signature : <code>char freePackedVector(PVrec**)</code> */
	unsigned char freePackedVector(PVrec** PV);
}
extern ""C"" {
/**
	 * Open the shared object FILE and map it in; return a handle that can be<br>
	 * passed to `dlsym' to get symbol values from it.<br>
	 * Original signature : <code>void* dlopen(const char*, int)</code>
	 */
	extern void* dlopen(const char* __file, int __mode);
	/**
	 * Unmap and close a shared object opened by `dlopen'.<br>
	 * The handle cannot be used again after calling `dlclose'.<br>
	 * Original signature : <code>int dlclose(void*)</code>
	 */
	extern int dlclose(void* __handle);
	/**
	 * Find the run-time address in the shared object HANDLE refers to<br>
	 * of the symbol called NAME.<br>
	 * Original signature : <code>void* dlsym(void*, const char*)</code>
	 */
	extern void* dlsym(void* __handle, const char* __name);
	/**
	 * When any of the above functions fails, call this function<br>
	 * to return a string describing the error.  Each call resets<br>
	 * the error string so that a following call returns null.<br>
	 * Original signature : <code>char* dlerror()</code>
	 */
	extern char* dlerror();
}
/** Sparse matrix element (ordered columnwise) */
typedef struct MATitem {
	int rownr;
	int colnr;
	double value;
} _MATitem;
typedef struct MATrec {
	/** Owner reference */
	lprec* lp;
	/** Active dimensions */
	int rows;
	int columns;
	/** Allocated memory */
	int rows_alloc;
	int columns_alloc;
	int mat_alloc; /* The allocated size for matrix sized structures */
	int* col_mat_colnr;
	int* col_mat_rownr;
	double* col_mat_value;
	int* col_end; /* columns_alloc+1 : col_end[i] is the index of the
                                   first element after column i; column[i] is stored
                                   in elements col_end[i-1] to col_end[i]-1 */
	int* col_tag; /* user-definable tag associated with each column */
	int* row_mat; /* mat_alloc : From index 0, row_mat contains the
                                   row-ordered index of the elements of col_mat */
	int* row_end; /* rows_alloc+1 : row_end[i] is the index of the
                                   first element in row_mat after row i */
	int* row_tag; /* user-definable tag associated with each row */
	double* colmax; /* Array of maximum values of each column */
	double* rowmax; /* Array of maximum values of each row */
	double epsvalue; /* Zero element rejection threshold */
	double infnorm; /* The largest absolute value in the matrix */
	double dynrange;
	unsigned char row_end_valid; /* TRUE if row_end & row_mat are valid */
	unsigned char is_roworder; /* TRUE if the current (temporary) matrix order is row-wise */
} _MATrec;
typedef struct DeltaVrec {
	lprec* lp;
	int activelevel;
	MATrec* tracker;
} _DeltaVrec;
/**
 * Sparse matrix routines<br>
 * Original signature : <code>MATrec* mat_create(lprec*, int, int, double)</code>
 */
MATrec* mat_create(lprec* lp, int rows, int columns, double epsvalue);
/** Original signature : <code>char mat_memopt(MATrec*, int, int, int)</code> */
unsigned char mat_memopt(MATrec* mat, int rowextra, int colextra, int nzextra);
/** Original signature : <code>void mat_free(MATrec**)</code> */
void mat_free(MATrec** matrix);
/** Original signature : <code>char inc_matrow_space(MATrec*, int)</code> */
unsigned char inc_matrow_space(MATrec* mat, int deltarows);
/** Original signature : <code>int mat_mapreplace(MATrec*, LLrec*, LLrec*, MATrec*)</code> */
int mat_mapreplace(MATrec* mat, LLrec* rowmap, LLrec* colmap, MATrec* insmat);
/** Original signature : <code>int mat_matinsert(MATrec*, MATrec*)</code> */
int mat_matinsert(MATrec* mat, MATrec* insmat);
/** Original signature : <code>int mat_zerocompact(MATrec*)</code> */
int mat_zerocompact(MATrec* mat);
/** Original signature : <code>int mat_rowcompact(MATrec*, unsigned char)</code> */
int mat_rowcompact(MATrec* mat, unsigned char dozeros);
/** Original signature : <code>int mat_colcompact(MATrec*, int, int)</code> */
int mat_colcompact(MATrec* mat, int prev_rows, int prev_cols);
/** Original signature : <code>char inc_matcol_space(MATrec*, int)</code> */
unsigned char inc_matcol_space(MATrec* mat, int deltacols);
/** Original signature : <code>char inc_mat_space(MATrec*, int)</code> */
unsigned char inc_mat_space(MATrec* mat, int mindelta);
/** Original signature : <code>int mat_shiftrows(MATrec*, int*, int, LLrec*)</code> */
int mat_shiftrows(MATrec* mat, int* bbase, int delta, LLrec* varmap);
/** Original signature : <code>int mat_shiftcols(MATrec*, int*, int, LLrec*)</code> */
int mat_shiftcols(MATrec* mat, int* bbase, int delta, LLrec* varmap);
/** Original signature : <code>MATrec* mat_extractmat(MATrec*, LLrec*, LLrec*, unsigned char)</code> */
MATrec* mat_extractmat(MATrec* mat, LLrec* rowmap, LLrec* colmap, unsigned char negated);
/** Original signature : <code>int mat_appendrow(MATrec*, int, double*, int*, double, unsigned char)</code> */
int mat_appendrow(MATrec* mat, int count, double* row, int* colno, double mult, unsigned char checkrowmode);
/** Original signature : <code>int mat_appendcol(MATrec*, int, double*, int*, double, unsigned char)</code> */
int mat_appendcol(MATrec* mat, int count, double* column, int* rowno, double mult, unsigned char checkrowmode);
/** Original signature : <code>char mat_get_data(lprec*, int, unsigned char, int**, int**, double**)</code> */
unsigned char mat_get_data(lprec* lp, int matindex, unsigned char isrow, int** rownr, int** colnr, double** value);
/** Original signature : <code>char mat_set_rowmap(MATrec*, int, int, int, int)</code> */
unsigned char mat_set_rowmap(MATrec* mat, int row_mat_index, int rownr, int colnr, int col_mat_index);
/** Original signature : <code>char mat_indexrange(MATrec*, int, unsigned char, int*, int*)</code> */
unsigned char mat_indexrange(MATrec* mat, int index, unsigned char isrow, int* startpos, int* endpos);
/** Original signature : <code>char mat_validate(MATrec*)</code> */
unsigned char mat_validate(MATrec* mat);
/** Original signature : <code>char mat_equalRows(MATrec*, int, int)</code> */
unsigned char mat_equalRows(MATrec* mat, int baserow, int comprow);
/** Original signature : <code>int mat_findelm(MATrec*, int, int)</code> */
int mat_findelm(MATrec* mat, int row, int column);
/** Original signature : <code>int mat_findins(MATrec*, int, int, int*, unsigned char)</code> */
int mat_findins(MATrec* mat, int row, int column, int* insertpos, unsigned char validate);
/** Original signature : <code>void mat_multcol(MATrec*, int, double, unsigned char)</code> */
void mat_multcol(MATrec* mat, int col_nr, double mult, unsigned char DoObj);
/** Original signature : <code>double mat_getitem(MATrec*, int, int)</code> */
double mat_getitem(MATrec* mat, int row, int column);
/** Original signature : <code>char mat_setitem(MATrec*, int, int, double)</code> */
unsigned char mat_setitem(MATrec* mat, int row, int column, double value);
/** Original signature : <code>char mat_additem(MATrec*, int, int, double)</code> */
unsigned char mat_additem(MATrec* mat, int row, int column, double delta);
/** Original signature : <code>char mat_setvalue(MATrec*, int, int, double, unsigned char)</code> */
unsigned char mat_setvalue(MATrec* mat, int Row, int Column, double Value, unsigned char doscale);
/** Original signature : <code>int mat_nonzeros(MATrec*)</code> */
int mat_nonzeros(MATrec* mat);
/** Original signature : <code>int mat_collength(MATrec*, int)</code> */
int mat_collength(MATrec* mat, int colnr);
/** Original signature : <code>int mat_rowlength(MATrec*, int)</code> */
int mat_rowlength(MATrec* mat, int rownr);
/** Original signature : <code>void mat_multrow(MATrec*, int, double)</code> */
void mat_multrow(MATrec* mat, int row_nr, double mult);
/** Original signature : <code>void mat_multadd(MATrec*, double*, int, double)</code> */
void mat_multadd(MATrec* mat, double* lhsvector, int varnr, double mult);
/** Original signature : <code>char mat_setrow(MATrec*, int, int, double*, int*, unsigned char, unsigned char)</code> */
unsigned char mat_setrow(MATrec* mat, int rowno, int count, double* row, int* colno, unsigned char doscale, unsigned char checkrowmode);
/** Original signature : <code>char mat_setcol(MATrec*, int, int, double*, int*, unsigned char, unsigned char)</code> */
unsigned char mat_setcol(MATrec* mat, int colno, int count, double* column, int* rowno, unsigned char doscale, unsigned char checkrowmode);
/** Original signature : <code>char mat_mergemat(MATrec*, MATrec*, unsigned char)</code> */
unsigned char mat_mergemat(MATrec* target, MATrec* source, unsigned char usecolmap);
/** Original signature : <code>int mat_checkcounts(MATrec*, int*, int*, unsigned char)</code> */
int mat_checkcounts(MATrec* mat, int* rownum, int* colnum, unsigned char freeonexit);
/** Original signature : <code>int mat_expandcolumn(MATrec*, int, double*, int*, unsigned char)</code> */
int mat_expandcolumn(MATrec* mat, int colnr, double* column, int* nzlist, unsigned char signedA);
/** Original signature : <code>char mat_computemax(MATrec*)</code> */
unsigned char mat_computemax(MATrec* mat);
/** Original signature : <code>char mat_transpose(MATrec*)</code> */
unsigned char mat_transpose(MATrec* mat);
/** Original signature : <code>WINAPI invert(lprec*, unsigned char, unsigned final char)</code> */
WINAPI invert(lprec* lp, unsigned char shiftbounds, unsigned final char char1);
/**
 * Vector compression and expansion routines<br>
 * Original signature : <code>char vec_compress(double*, int, int, double, double*, int*)</code>
 */
unsigned char vec_compress(double* densevector, int startpos, int endpos, double epsilon, double* nzvector, int* nzindex);
/** Original signature : <code>char vec_expand(double*, int*, double*, int, int)</code> */
unsigned char vec_expand(double* nzvector, int* nzindex, double* densevector, int startpos, int endpos);
/**
 * Sparse matrix products<br>
 * Original signature : <code>char get_colIndexA(lprec*, int, int*, unsigned char)</code>
 */
unsigned char get_colIndexA(lprec* lp, int varset, int* colindex, unsigned char append);
/** Original signature : <code>int prod_Ax(lprec*, int*, double*, int*, double, double, double*, int*, int)</code> */
int prod_Ax(lprec* lp, int* coltarget, double* input, int* nzinput, double roundzero, double ofscalar, double* output, int* nzoutput, int roundmode);
/** Original signature : <code>int prod_xA(lprec*, int*, double*, int*, double, double, double*, int*, int)</code> */
int prod_xA(lprec* lp, int* coltarget, double* input, int* nzinput, double roundzero, double ofscalar, double* output, int* nzoutput, int roundmode);
/** Original signature : <code>char prod_xA2(lprec*, int*, double*, double, int*, double*, double, int*, double, int)</code> */
unsigned char prod_xA2(lprec* lp, int* coltarget, double* prow, double proundzero, int* pnzprow, double* drow, double droundzero, int* dnzdrow, double ofscalar, int roundmode);
/**
 * Equation solution<br>
 * Original signature : <code>char fimprove(lprec*, double*, int*, double)</code>
 */
unsigned char fimprove(lprec* lp, double* pcol, int* nzidx, double roundzero);
/** Original signature : <code>void ftran(lprec*, double*, int*, double)</code> */
void ftran(lprec* lp, double* rhsvector, int* nzidx, double roundzero);
/** Original signature : <code>char bimprove(lprec*, double*, int*, double)</code> */
unsigned char bimprove(lprec* lp, double* rhsvector, int* nzidx, double roundzero);
/** Original signature : <code>void btran(lprec*, double*, int*, double)</code> */
void btran(lprec* lp, double* rhsvector, int* nzidx, double roundzero);
/**
 * Combined equation solution and matrix product for simplex operations<br>
 * Original signature : <code>char fsolve(lprec*, int, double*, int*, double, double, unsigned char)</code>
 */
unsigned char fsolve(lprec* lp, int varin, double* pcol, int* nzidx, double roundzero, double ofscalar, unsigned char prepareupdate);
/** Original signature : <code>char bsolve(lprec*, int, double*, int*, double, double)</code> */
unsigned char bsolve(lprec* lp, int row_nr, double* rhsvector, int* nzidx, double roundzero, double ofscalar);
/** Original signature : <code>void bsolve_xA2(lprec*, int*, int, double*, double, int*, int, double*, double, int*, int)</code> */
void bsolve_xA2(lprec* lp, int* coltarget, int row_nr1, double* vector1, double roundzero1, int* nzvector1, int row_nr2, double* vector2, double roundzero2, int* nzvector2, int roundmode);
/**
 * Change-tracking routines (primarily for B&B and presolve)<br>
 * Original signature : <code>DeltaVrec* createUndoLadder(lprec*, int, int)</code>
 */
DeltaVrec* createUndoLadder(lprec* lp, int levelitems, int maxlevels);
/** Original signature : <code>int incrementUndoLadder(DeltaVrec*)</code> */
int incrementUndoLadder(DeltaVrec* DV);
/** Original signature : <code>char modifyUndoLadder(DeltaVrec*, int, double[], double)</code> */
unsigned char modifyUndoLadder(DeltaVrec* DV, int itemno, double target[], double newvalue);
/** Original signature : <code>int countsUndoLadder(DeltaVrec*)</code> */
int countsUndoLadder(DeltaVrec* DV);
/** Original signature : <code>int restoreUndoLadder(DeltaVrec*, double[])</code> */
int restoreUndoLadder(DeltaVrec* DV, double target[]);
/** Original signature : <code>int decrementUndoLadder(DeltaVrec*)</code> */
int decrementUndoLadder(DeltaVrec* DV);
/** Original signature : <code>char freeUndoLadder(DeltaVrec**)</code> */
unsigned char freeUndoLadder(DeltaVrec** DV);
/**
 * Specialized presolve undo functions<br>
 * Original signature : <code>char appendUndoPresolve(lprec*, unsigned char, double, int)</code>
 */
unsigned char appendUndoPresolve(lprec* lp, unsigned char isprimal, double beta, int colnrDep);
/** Original signature : <code>char addUndoPresolve(lprec*, unsigned char, int, double, double, int)</code> */
unsigned char addUndoPresolve(lprec* lp, unsigned char isprimal, int colnrElim, double alpha, double beta, int colnrDep);
/** Basis storage (mainly for B&B) */
typedef struct basisrec {
	int level;
	int* var_basic;
	unsigned char* is_basic;
	unsigned char* is_lower;
	int pivots;
	_basisrec* previous;
} _basisrec;
/** Presolve undo data storage */
typedef struct presolveundorec {
	lprec* lp;
	int orig_rows;
	int orig_columns;
	int orig_sum;
	int* var_to_orig; /* sum_alloc+1 : Mapping of variables from solution to
                                   best_solution to account for removed variables and
                                   rows during presolve; a non-positive value indicates
                                   that the constraint or variable was removed */
	int* orig_to_var; /* sum_alloc+1 : Mapping from original variable index to
                                   current / working index number */
	double* fixed_rhs; /* rows_alloc+1 : Storage of values of presolved fixed colums */
	double* fixed_obj; /* columns_alloc+1: Storage of values of presolved fixed rows */
	DeltaVrec* deletedA; /* A matrix of eliminated data from matA */
	DeltaVrec* primalundo; /* Affine translation vectors for eliminated primal variables */
	DeltaVrec* dualundo; /* Affine translation vectors for eliminated dual variables */
	unsigned char OFcolsdeleted;
} _presolveundorec;
/** Pseudo-cost arrays used during B&B */
typedef struct BBPSrec {
	lprec* lp;
	int pseodotype;
	int updatelimit;
	int updatesfinished;
	double restartlimit;
	MATitem* UPcost;
	MATitem* LOcost;
	_BBPSrec* secondary;
} _BBPSrec;
/** Bounds storage for B&B routines */
typedef struct BBrec {
	_BBrec* parent;
	_BBrec* child;
	lprec* lp;
	int varno;
	int vartype;
	int lastvarcus; /* Count of non-int variables of the previous branch */
	int lastrcf;
	int nodesleft;
	int nodessolved;
	int nodestatus;
	double noderesult;
	double lastsolution; /* Optimal solution of the previous branch */
	double sc_bound;
	double* upbo;
	double* lowbo;
	double UPbound;
	double LObound;
	int UBtrack; /* Signals that incoming bounds were changed */
	int LBtrack; /* Signals that incoming bounds were changed */
	unsigned char contentmode; /* Flag indicating if we "own" the bound vectors */
	unsigned char sc_canset;
	unsigned char isSOS;
	unsigned char isGUB;
	int* varmanaged; /* Extended list of variables managed by this B&B level */
	unsigned char isfloor; /* State variable indicating the active B&B bound */
	unsigned char UBzerobased; /* State variable indicating if bounds have been rebased */
} _BBrec;
extern ""C"" {
/** Original signature : <code>BBrec* create_BB(lprec*, BBrec*, unsigned char)</code> */
	BBrec* create_BB(lprec* lp, BBrec* parentBB, unsigned char dofullcopy);
	/** Original signature : <code>BBrec* push_BB(lprec*, BBrec*, int, int, int)</code> */
	BBrec* push_BB(lprec* lp, BBrec* parentBB, int varno, int vartype, int varcus);
	/** Original signature : <code>char initbranches_BB(BBrec*)</code> */
	unsigned char initbranches_BB(BBrec* BB);
	/** Original signature : <code>char fillbranches_BB(BBrec*)</code> */
	unsigned char fillbranches_BB(BBrec* BB);
	/** Original signature : <code>char nextbranch_BB(BBrec*)</code> */
	unsigned char nextbranch_BB(BBrec* BB);
	/** Original signature : <code>char strongbranch_BB(lprec*, BBrec*, int, int, int)</code> */
	unsigned char strongbranch_BB(lprec* lp, BBrec* BB, int varno, int vartype, int varcus);
	/** Original signature : <code>char initcuts_BB(lprec*)</code> */
	unsigned char initcuts_BB(lprec* lp);
	/** Original signature : <code>int updatecuts_BB(lprec*)</code> */
	int updatecuts_BB(lprec* lp);
	/** Original signature : <code>char freecuts_BB(lprec*)</code> */
	unsigned char freecuts_BB(lprec* lp);
	/** Original signature : <code>BBrec* findself_BB(BBrec*)</code> */
	BBrec* findself_BB(BBrec* BB);
	/** Original signature : <code>int solve_LP(lprec*, BBrec*)</code> */
	int solve_LP(lprec* lp, BBrec* BB);
	/** Original signature : <code>int rcfbound_BB(BBrec*, int, unsigned char, double*, unsigned char*)</code> */
	int rcfbound_BB(BBrec* BB, int varno, unsigned char isINT, double* newbound, unsigned char* isfeasible);
	/** Original signature : <code>char findnode_BB(BBrec*, int*, int*, int*)</code> */
	unsigned char findnode_BB(BBrec* BB, int* varno, int* vartype, int* varcus);
	/** Original signature : <code>int solve_BB(BBrec*)</code> */
	int solve_BB(BBrec* BB);
	/** Original signature : <code>char free_BB(BBrec**)</code> */
	unsigned char free_BB(BBrec** BB);
	/** Original signature : <code>BBrec* pop_BB(BBrec*)</code> */
	BBrec* pop_BB(BBrec* BB);
	/** Original signature : <code>int run_BB(lprec*)</code> */
	int run_BB(lprec* lp);
}
/** Partial pricing block data */
typedef struct partialrec {
	lprec* lp;
	int blockcount; /* ## The number of logical blocks or stages in the model */
	int blocknow; /* The currently active block */
	int* blockend; /* Array of column indeces giving the start of each block */
	int* blockpos; /* Array of column indeces giving the start scan position */
	unsigned char isrow;
} _partialrec;
typedef _SOSgroup SOSgroup;
typedef struct SOSrec {
	SOSgroup* parent;
	int tagorder;
	char* name;
	int type;
	unsigned char isGUB;
	int size;
	int priority;
	int* members;
	double* weights;
	int* membersSorted;
	int* membersMapped;
} _SOSrec;
/** typedef */
struct _SOSgroup {
	lprec* lp; /* Pointer to owner */
	SOSrec** sos_list; /* Array of pointers to SOS lists */
	int sos_alloc; /* Size allocated to specially ordered sets (SOS1, SOS2...) */
	int sos_count; /* Number of specially ordered sets (SOS1, SOS2...) */
	int maxorder; /* The highest-order SOS in the group */
	int sos1_count; /* Number of the lowest order SOS in the group */
	int* membership; /* Array of variable-sorted indeces to SOSes that the variable is member of */
	int* memberpos; /* Starting positions of the each column's membership list */
};
extern ""C"" {
/**
	 * SOS storage structure<br>
	 * Original signature : <code>SOSgroup* create_SOSgroup(lprec*)</code>
	 */
	SOSgroup* create_SOSgroup(lprec* lp);
	/** Original signature : <code>void resize_SOSgroup(SOSgroup*)</code> */
	void resize_SOSgroup(SOSgroup* group);
	/** Original signature : <code>int append_SOSgroup(SOSgroup*, SOSrec*)</code> */
	int append_SOSgroup(SOSgroup* group, SOSrec* SOS);
	/** Original signature : <code>int clean_SOSgroup(SOSgroup*, unsigned char)</code> */
	int clean_SOSgroup(SOSgroup* group, unsigned char forceupdatemap);
	/** Original signature : <code>void free_SOSgroup(SOSgroup**)</code> */
	void free_SOSgroup(SOSgroup** group);
	/** Original signature : <code>SOSrec* create_SOSrec(SOSgroup*, char*, int, int, int, int*, double*)</code> */
	SOSrec* create_SOSrec(SOSgroup* group, char* name, int type, int priority, int size, int* variables, double* weights);
	/** Original signature : <code>char delete_SOSrec(SOSgroup*, int)</code> */
	unsigned char delete_SOSrec(SOSgroup* group, int sosindex);
	/** Original signature : <code>int append_SOSrec(SOSrec*, int, int*, double*)</code> */
	int append_SOSrec(SOSrec* SOS, int size, int* variables, double* weights);
	/** Original signature : <code>void free_SOSrec(SOSrec*)</code> */
	void free_SOSrec(SOSrec* SOS);
	/**
	 * SOS utilities<br>
	 * Original signature : <code>int make_SOSchain(lprec*, unsigned char)</code>
	 */
	int make_SOSchain(lprec* lp, unsigned char forceresort);
	/** Original signature : <code>int SOS_member_updatemap(SOSgroup*)</code> */
	int SOS_member_updatemap(SOSgroup* group);
	/** Original signature : <code>char SOS_member_sortlist(SOSgroup*, int)</code> */
	unsigned char SOS_member_sortlist(SOSgroup* group, int sosindex);
	/** Original signature : <code>char SOS_shift_col(SOSgroup*, int, int, int, LLrec*, unsigned char)</code> */
	unsigned char SOS_shift_col(SOSgroup* group, int sosindex, int column, int delta, LLrec* usedmap, unsigned char forceresort);
	/** Original signature : <code>int SOS_member_delete(SOSgroup*, int, int)</code> */
	int SOS_member_delete(SOSgroup* group, int sosindex, int member);
	/** Original signature : <code>int SOS_get_type(SOSgroup*, int)</code> */
	int SOS_get_type(SOSgroup* group, int sosindex);
	/** Original signature : <code>int SOS_infeasible(SOSgroup*, int)</code> */
	int SOS_infeasible(SOSgroup* group, int sosindex);
	/** Original signature : <code>int SOS_member_index(SOSgroup*, int, int)</code> */
	int SOS_member_index(SOSgroup* group, int sosindex, int member);
	/** Original signature : <code>int SOS_member_count(SOSgroup*, int)</code> */
	int SOS_member_count(SOSgroup* group, int sosindex);
	/** Original signature : <code>int SOS_memberships(SOSgroup*, int)</code> */
	int SOS_memberships(SOSgroup* group, int column);
	/** Original signature : <code>int* SOS_get_candidates(SOSgroup*, int, int, unsigned char, double*, double*)</code> */
	int* SOS_get_candidates(SOSgroup* group, int sosindex, int column, unsigned char excludetarget, double* upbound, double* lobound);
	/** Original signature : <code>int SOS_is_member(SOSgroup*, int, int)</code> */
	int SOS_is_member(SOSgroup* group, int sosindex, int column);
	/** Original signature : <code>char SOS_is_member_of_type(SOSgroup*, int, int)</code> */
	unsigned char SOS_is_member_of_type(SOSgroup* group, int column, int sostype);
	/** Original signature : <code>char SOS_set_GUB(SOSgroup*, int, unsigned char)</code> */
	unsigned char SOS_set_GUB(SOSgroup* group, int sosindex, unsigned char state);
	/** Original signature : <code>char SOS_is_GUB(SOSgroup*, int)</code> */
	unsigned char SOS_is_GUB(SOSgroup* group, int sosindex);
	/** Original signature : <code>char SOS_is_marked(SOSgroup*, int, int)</code> */
	unsigned char SOS_is_marked(SOSgroup* group, int sosindex, int column);
	/** Original signature : <code>char SOS_is_active(SOSgroup*, int, int)</code> */
	unsigned char SOS_is_active(SOSgroup* group, int sosindex, int column);
	/** Original signature : <code>char SOS_is_full(SOSgroup*, int, int, unsigned char)</code> */
	unsigned char SOS_is_full(SOSgroup* group, int sosindex, int column, unsigned char activeonly);
	/** Original signature : <code>char SOS_can_activate(SOSgroup*, int, int)</code> */
	unsigned char SOS_can_activate(SOSgroup* group, int sosindex, int column);
	/** Original signature : <code>char SOS_set_marked(SOSgroup*, int, int, unsigned char)</code> */
	unsigned char SOS_set_marked(SOSgroup* group, int sosindex, int column, unsigned char asactive);
	/** Original signature : <code>char SOS_unmark(SOSgroup*, int, int)</code> */
	unsigned char SOS_unmark(SOSgroup* group, int sosindex, int column);
	/** Original signature : <code>int SOS_fix_unmarked(SOSgroup*, int, int, double*, double, unsigned char, int*, DeltaVrec*)</code> */
	int SOS_fix_unmarked(SOSgroup* group, int sosindex, int variable, double* bound, double value, unsigned char isupper, int* diffcount, DeltaVrec* changelog);
	/** Original signature : <code>int SOS_fix_list(SOSgroup*, int, int, double*, int*, unsigned char, DeltaVrec*)</code> */
	int SOS_fix_list(SOSgroup* group, int sosindex, int variable, double* bound, int* varlist, unsigned char isleft, DeltaVrec* changelog);
	/** Original signature : <code>int SOS_is_satisfied(SOSgroup*, int, double*)</code> */
	int SOS_is_satisfied(SOSgroup* group, int sosindex, double* solution);
	/** Original signature : <code>char SOS_is_feasible(SOSgroup*, int, double*)</code> */
	unsigned char SOS_is_feasible(SOSgroup* group, int sosindex, double* solution);
}
/** ------------------------------------------------------------------------- */
typedef int WINAPI(lprec* lp, void* userhandle);
typedef void WINAPI(lprec* lp, void* userhandle, char* buf);
typedef void WINAPI(lprec* lp, void* userhandle, int message);
typedef int WINAPI(lprec* lp, void* userhandle, int message);
/** ------------------------------------------------------------------------- */
typedef unsigned char WINAPI(lprec* lp, double* column);
typedef unsigned char WINAPI(lprec* lp, int count, double* column, int* rowno);
typedef unsigned char WINAPI(lprec* lp, double* row, int constr_type, double rh);
typedef unsigned char WINAPI(lprec* lp, int count, double* row, int* colno, int constr_type, double rh);
typedef unsigned char WINAPI(lprec* lp, double* row, int con_type, double rhs);
typedef int WINAPI(lprec* lp, char* name, int sostype, int priority, int count, int* sosvars, double* weights);
typedef int WINAPI(lprec* lp, double* column);
typedef lprec *(WINAPI)(lprec* lp);
typedef void WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, int colnr);
typedef unsigned char WINAPI(lprec* lp, int rownr);
typedef void WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp);
typedef void WINAPI(lprec** plp);
typedef int WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, int* bascolumn, unsigned char nonbasic);
typedef int WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp);
typedef double WINAPI(lprec* lp);
typedef char *(WINAPI)(lprec* lp, int colnr);
typedef unsigned char WINAPI(lprec* lp, int colnr, double* column);
typedef int WINAPI(lprec* lp, int colnr, double* column, int* nzrow);
typedef int WINAPI(lprec* lp, int rownr);
typedef double WINAPI(lprec* lp, int rownr, int count, double* primsolution, int* nzindex);
typedef unsigned char WINAPI(lprec* lp, double* constr);
typedef unsigned char WINAPI(lprec* lp, double* rc);
typedef double WINAPI(lprec* lp);
typedef double WINAPI(lprec* lp);
typedef double WINAPI(lprec* lp);
typedef double WINAPI(lprec* lp);
typedef double WINAPI(lprec* lp);
typedef double WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef double WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, double* lambda);
typedef double WINAPI(lprec* lp, int colnr);
typedef int WINAPI(lprec* lp, int orig_index);
typedef char *(WINAPI)(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef double WINAPI(lprec* lp, int rownr, int colnr);
typedef double WINAPI(lprec* lp, int matindex, unsigned char isrow, unsigned char adjustsign);
typedef int WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef double WINAPI(lprec* lp, unsigned char absolute);
typedef int WINAPI(lprec* lp, unsigned char getabssize);
typedef unsigned char WINAPI(lprec* lp, unsigned char isrow);
typedef void WINAPI(lprec* lp, unsigned char isrow, unsigned char use_names);
typedef int WINAPI(lprec* lp, char* varname, unsigned char isrow);
typedef int WINAPI(lprec* lp);
typedef double WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef double WINAPI(lprec* lp);
typedef double WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp, int lp_index);
typedef char *(WINAPI)(lprec* lp, int colnr);
typedef char *(WINAPI)(lprec* lp, int rownr);
typedef void WINAPI(lprec* lp, int* blockcount, int* blockstart, unsigned char isrow);
typedef int WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, double* pv);
typedef int WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, double* clower, double* cupper, int* updatelimit);
typedef unsigned char WINAPI(lprec* lp, double** constr);
typedef unsigned char WINAPI(lprec* lp, double** rc);
typedef unsigned char WINAPI(lprec* lp, double** lambda);
typedef unsigned char WINAPI(lprec* lp, double** pv);
typedef unsigned char WINAPI(lprec* lp, double** objfrom, double** objtill);
typedef unsigned char WINAPI(lprec* lp, double** objfrom, double** objtill, double** objfromvalue, double** objtillvalue);
typedef unsigned char WINAPI(lprec* lp, double** duals, double** dualsfrom, double** dualstill);
typedef unsigned char WINAPI(lprec* lp, double** var);
typedef double WINAPI(lprec* lp, int rownr);
typedef double WINAPI(lprec* lp, int rownr);
typedef int WINAPI(lprec* lp, int rownr, double* row, int* colno);
typedef unsigned char WINAPI(lprec* lp, int rownr, double* row);
typedef char *(WINAPI)(lprec* lp, int rownr);
typedef double WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, double* objfrom, double* objtill);
typedef unsigned char WINAPI(lprec* lp, double* objfrom, double* objtill, double* objfromvalue, double* objtillvalue);
typedef unsigned char WINAPI(lprec* lp, double* duals, double* dualsfrom, double* dualstill);
typedef int WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef int WINAPI(lprec* lp);
typedef char *(WINAPI)(lprec* lp, int statuscode);
typedef long WINAPI(lprec* lp);
typedef long long WINAPI(lprec* lp);
typedef long long WINAPI(lprec* lp);
typedef double WINAPI(lprec* lp, int colnr);
typedef int WINAPI(lprec* lp, int colnr);
typedef double WINAPI(lprec* lp, int index);
typedef double WINAPI(lprec* lp, int index);
typedef int WINAPI(lprec* lp, int colnr);
typedef unsigned char WINAPI(lprec* lp, double* var);
typedef int WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, double* guessvector, int* basisvector);
typedef double WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, int testmask);
typedef unsigned char WINAPI(lprec* lp, int colnr);
typedef unsigned char WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, int rownr, int mask);
typedef unsigned char WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, double* values, double threshold);
typedef unsigned char WINAPI(lprec* lp, int colnr);
typedef unsigned char WINAPI(lprec* lp, double value);
typedef unsigned char WINAPI(lprec* lp, int column);
typedef unsigned char WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, int colnr);
typedef unsigned char WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, int testmask);
typedef unsigned char WINAPI(lprec* lp, int rule);
typedef unsigned char WINAPI(lprec* lp, int testmask);
typedef unsigned char WINAPI(lprec* lp, int testmask);
typedef unsigned char WINAPI(lprec* lp, int scaletype);
typedef unsigned char WINAPI(lprec* lp, int colnr);
typedef unsigned char WINAPI(lprec* lp, int colnr);
typedef unsigned char WINAPI(lprec* lp);
typedef void WINAPI(int* majorversion, int* minorversion, int* release, int* build);
typedef lprec *(WINAPI)(int rows, int columns);
typedef void WINAPI(lprec* lp, int columns);
typedef unsigned char WINAPI(lprec* lp, char* filename);
typedef void WINAPI(lprec* lp);
typedef void WINAPI(lprec* lp);
typedef void WINAPI(lprec* lp);
typedef void WINAPI(lprec* lp);
typedef void WINAPI(lprec* lp, int columns);
typedef void WINAPI(lprec* lp, char* str);
typedef void WINAPI(lprec* lp);
typedef void WINAPI(lprec* lp, lphandle_intfunc newctrlc, void* ctrlchandle);
typedef void WINAPI(lprec* lp, lphandleint_intfunc newnode, void* bbnodehandle);
typedef void WINAPI(lprec* lp, lphandleint_intfunc newbranch, void* bbbranchhandle);
typedef void WINAPI(lprec* lp, lphandlestr_func newlog, void* loghandle);
typedef void WINAPI(lprec* lp, lphandleint_func newmsg, void* msghandle, int mask);
typedef lprec *(WINAPI)(char* filename, int verbose, char* lp_name);
typedef lprec *(WINAPI)(char* filename, int options);
typedef lprec *(WINAPI)(char* xliname, char* modelname, char* dataname, char* options, int verbose);
typedef unsigned char WINAPI(lprec* lp, char* filename, char* info);
typedef void WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, char* filename, char* options);
typedef void WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, int rows, int columns);
typedef unsigned char WINAPI(lprec* lp, unsigned char turnon);
typedef void WINAPI(lprec* lp, int anti_degen);
typedef int WINAPI(lprec* lp, int basisPos, int enteringCol);
typedef unsigned char WINAPI(lprec* lp, int* bascolumn, unsigned char nonbasic);
typedef void WINAPI(lprec* lp, int mode);
typedef void WINAPI(lprec* lp, int bb_maxlevel);
typedef void WINAPI(lprec* lp, int bb_floorfirst);
typedef void WINAPI(lprec* lp, int bb_rule);
typedef unsigned char WINAPI(lprec* lp, char* filename);
typedef unsigned char WINAPI(lprec* lp, int colnr, unsigned char must_be_bin);
typedef unsigned char WINAPI(lprec* lp, int colnr, double lower, double upper);
typedef void WINAPI(lprec* lp, unsigned char tighten);
typedef void WINAPI(lprec* lp, unsigned char break_at_first);
typedef void WINAPI(lprec* lp, double break_at_value);
typedef unsigned char WINAPI(lprec* lp, int colnr, double* column);
typedef unsigned char WINAPI(lprec* lp, int colnr, int count, double* column, int* rowno);
typedef unsigned char WINAPI(lprec* lp, int colnr, char* new_name);
typedef unsigned char WINAPI(lprec* lp, int rownr, int con_type);
typedef void WINAPI(lprec* lp, unsigned char debug);
typedef void WINAPI(lprec* lp, double epsb);
typedef void WINAPI(lprec* lp, double epsd);
typedef void WINAPI(lprec* lp, double epsel);
typedef void WINAPI(lprec* lp, double epsint);
typedef unsigned char WINAPI(lprec* lp, int epslevel);
typedef void WINAPI(lprec* lp, double epsperturb);
typedef void WINAPI(lprec* lp, double epspivot);
typedef unsigned char WINAPI(lprec* lp, int colnr);
typedef void WINAPI(lprec* lp, int improve);
typedef void WINAPI(lprec* lp, double infinite);
typedef unsigned char WINAPI(lprec* lp, int colnr, unsigned char must_be_int);
typedef void WINAPI(lprec* lp, unsigned char lag_trace);
typedef unsigned char WINAPI(lprec* lp, int colnr, double value);
typedef unsigned char WINAPI(lprec* lp, char* lpname);
typedef unsigned char WINAPI(lprec* lp, int row, int column, double value);
typedef void WINAPI(lprec* lp);
typedef void WINAPI(lprec* lp, int max_num_inv);
typedef void WINAPI(lprec* lp);
typedef void WINAPI(lprec* lp, unsigned char absolute, double mip_gap);
typedef unsigned char WINAPI(lprec* lp, int multiblockdiv);
typedef void WINAPI(lprec* lp, double negrange);
typedef unsigned char WINAPI(lprec* lp, int colnr, double value);
typedef void WINAPI(lprec* lp, double obj_bound);
typedef unsigned char WINAPI(lprec* lp, double* row);
typedef unsigned char WINAPI(lprec* lp, int count, double* row, int* colno);
typedef void WINAPI(lprec* lp, unsigned char obj_in_basis);
typedef unsigned char WINAPI(lprec* lp, char* filename);
typedef void WINAPI(lprec* lp, FILE* stream);
typedef unsigned char WINAPI(lprec* lp, int blockcount, int* blockstart, unsigned char isrow);
typedef void WINAPI(lprec* lp, int piv_rule);
typedef void WINAPI(lprec* lp, unsigned char dodual);
typedef void WINAPI(lprec* lp, int presolvemode, int maxloops);
typedef void WINAPI(lprec* lp, int print_sol);
typedef unsigned char WINAPI(lprec* lp, double* clower, double* cupper, int* updatelimit);
typedef unsigned char WINAPI(lprec* lp, int rownr, double value);
typedef unsigned char WINAPI(lprec* lp, int rownr, double deltavalue);
typedef void WINAPI(lprec* lp, double* rh);
typedef unsigned char WINAPI(lprec* lp, int rownr, double* row);
typedef unsigned char WINAPI(lprec* lp, int rownr, int count, double* row, int* colno);
typedef unsigned char WINAPI(lprec* lp, int rownr, char* new_name);
typedef void WINAPI(lprec* lp, double scalelimit);
typedef void WINAPI(lprec* lp, int scalemode);
typedef unsigned char WINAPI(lprec* lp, int colnr, unsigned char must_be_sc);
typedef void WINAPI(lprec* lp, unsigned char maximize);
typedef void WINAPI(lprec* lp, int simplextype);
typedef void WINAPI(lprec* lp, int limit);
typedef void WINAPI(lprec* lp, long sectimeout);
typedef void WINAPI(lprec* lp, unsigned char trace);
typedef unsigned char WINAPI(lprec* lp, int colnr, double value);
typedef unsigned char WINAPI(lprec* lp, int colnr, int branch_mode);
typedef unsigned char WINAPI(lprec* lp, double* weights);
typedef void WINAPI(lprec* lp, int verbose);
typedef unsigned char WINAPI(lprec* lp, char* filename);
typedef int WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, char* col_string);
typedef unsigned char WINAPI(lprec* lp, char* row_string, int constr_type, double rh);
typedef unsigned char WINAPI(lprec* lp, char* row_string, int con_type, double rhs);
typedef unsigned char WINAPI(lprec* lp, char* row_string);
typedef unsigned char WINAPI(lprec* lp, char* rh_string);
typedef double WINAPI(lprec* lp);
typedef void WINAPI(lprec* lp);
typedef unsigned char WINAPI(lprec* lp, char* filename);
typedef unsigned char WINAPI(lprec* lp, FILE* output);
typedef unsigned char WINAPI(lprec* lp, char* filename);
typedef unsigned char WINAPI(lprec* lp, FILE* output);
typedef unsigned char WINAPI(lprec* lp, char* filename);
typedef unsigned char WINAPI(lprec* lp, FILE* output);
typedef unsigned char WINAPI(lprec* lp, char* filename, char* options, unsigned char results);
typedef unsigned char WINAPI(lprec* lp, char* filename);
typedef unsigned char WINAPI(lprec* lp, char* filename, char* options);
/** ------------------------------------------------------------------------- */
typedef unsigned char WINAPI(lprec* lp, int level);
typedef void reportfunc(lprec* lp, int level, char* format, ...);
typedef char *(__cdecl explainfunc)(lprec* lp, char* format, ...);
typedef int WINAPI(lprec* lp, int varin, double* pcol, int* nzlist, int* maxabs);
typedef int WINAPI(lprec* lp, int j, int rn[], double bj[]);
typedef double WINAPI(lprec* lp, int varnr, double mult);
typedef int WINAPI(lprec* lp, unsigned char* usedpos, int* colorder, int* size, unsigned char symmetric);
typedef unsigned char WINAPI(lprec* lp, unsigned char shiftbounds, unsigned final char char1);
typedef void WINAPI(int* actionvar, int actionmask);
typedef unsigned char WINAPI(int actionvar, int testmask);
typedef void WINAPI(int* actionvar, int actionmask);
/** ------------------------------------------------------------------------- */
typedef char *(BFPchar)();
typedef void BFP_lp(lprec* lp);
typedef void BFP_lpint(lprec* lp, int newsize);
typedef int BFPint_lp(lprec* lp);
typedef int BFPint_lpint(lprec* lp, int kind);
typedef double BFPreal_lp(lprec* lp);
typedef double *(BFPrealp_lp)(lprec* lp);
typedef void BFP_lpbool(lprec* lp, unsigned char maximum);
typedef int BFPint_lpbool(lprec* lp, unsigned char maximum);
typedef int BFPint_lpintintboolbool(lprec* lp, int uservars, int Bsize, unsigned char* usedpos, unsigned final char char1);
typedef void BFP_lprealint(lprec* lp, double* pcol, int* nzidx);
typedef void BFP_lprealintrealint(lprec* lp, double* prow, int* pnzidx, double* drow, int* dnzidx);
typedef unsigned char BFPbool_lp(lprec* lp);
typedef unsigned char BFPbool_lpbool(lprec* lp, unsigned char changesign);
typedef unsigned char BFPbool_lpint(lprec* lp, int size);
typedef unsigned char BFPbool_lpintintchar(lprec* lp, int size, int deltasize, char* options);
typedef unsigned char BFPbool_lpintintint(lprec* lp, int size, int deltasize, int sizeofvar);
typedef double BFPlreal_lpintintreal(lprec* lp, int row_nr, int col_nr, double* pcol);
typedef double BFPreal_lplrealreal(lprec* lp, double theta, double* pcol);
typedef int getcolumnex_func(lprec* lp, int colnr, double* nzvalues, int* nzrows, int* mapin);
typedef int BFPint_lpintrealcbintint(lprec* lp, int items, getcolumnex_func cb, int* maprow, int* mapcol);
/** ------------------------------------------------------------------------- */
typedef char *(XLIchar)();
typedef unsigned char XLIbool_lpintintint(lprec* lp, int size, int deltasize, int sizevar);
typedef unsigned char XLIbool_lpcharcharcharint(lprec* lp, char* modelname, char* dataname, char* options, int verbose);
typedef unsigned char XLIbool_lpcharcharbool(lprec* lp, char* filename, char* options, unsigned char results);
/** ------------------------------------------------------------------------- */
struct _lprec {
	/** Full list of exported functions made available in a quasi object-oriented fashion */
	add_column_func* add_column;
	add_columnex_func* add_columnex;
	add_constraint_func* add_constraint;
	add_constraintex_func* add_constraintex;
	add_lag_con_func* add_lag_con;
	add_SOS_func* add_SOS;
	column_in_lp_func* column_in_lp;
	copy_lp_func* copy_lp;
	default_basis_func* default_basis;
	del_column_func* del_column;
	del_constraint_func* del_constraint;
	delete_lp_func* delete_lp;
	dualize_lp_func* dualize_lp;
	free_lp_func* free_lp;
	get_anti_degen_func* get_anti_degen;
	get_basis_func* get_basis;
	get_basiscrash_func* get_basiscrash;
	get_bb_depthlimit_func* get_bb_depthlimit;
	get_bb_floorfirst_func* get_bb_floorfirst;
	get_bb_rule_func* get_bb_rule;
	get_bounds_tighter_func* get_bounds_tighter;
	get_break_at_value_func* get_break_at_value;
	get_col_name_func* get_col_name;
	get_columnex_func* get_columnex;
	get_constr_type_func* get_constr_type;
	get_constr_value_func* get_constr_value;
	get_constraints_func* get_constraints;
	get_dual_solution_func* get_dual_solution;
	get_epsb_func* get_epsb;
	get_epsd_func* get_epsd;
	get_epsel_func* get_epsel;
	get_epsint_func* get_epsint;
	get_epsperturb_func* get_epsperturb;
	get_epspivot_func* get_epspivot;
	get_improve_func* get_improve;
	get_infinite_func* get_infinite;
	get_lambda_func* get_lambda;
	get_lowbo_func* get_lowbo;
	get_lp_index_func* get_lp_index;
	get_lp_name_func* get_lp_name;
	get_Lrows_func* get_Lrows;
	get_mat_func* get_mat;
	get_mat_byindex_func* get_mat_byindex;
	get_max_level_func* get_max_level;
	get_maxpivot_func* get_maxpivot;
	get_mip_gap_func* get_mip_gap;
	get_multiprice_func* get_multiprice;
	get_nameindex_func* get_nameindex;
	get_Ncolumns_func* get_Ncolumns;
	get_negrange_func* get_negrange;
	get_nz_func* get_nonzeros;
	get_Norig_columns_func* get_Norig_columns;
	get_Norig_rows_func* get_Norig_rows;
	get_Nrows_func* get_Nrows;
	get_obj_bound_func* get_obj_bound;
	get_objective_func* get_objective;
	get_orig_index_func* get_orig_index;
	get_origcol_name_func* get_origcol_name;
	get_origrow_name_func* get_origrow_name;
	get_partialprice_func* get_partialprice;
	get_pivoting_func* get_pivoting;
	get_presolve_func* get_presolve;
	get_presolveloops_func* get_presolveloops;
	get_primal_solution_func* get_primal_solution;
	get_print_sol_func* get_print_sol;
	get_pseudocosts_func* get_pseudocosts;
	get_ptr_constraints_func* get_ptr_constraints;
	get_ptr_dual_solution_func* get_ptr_dual_solution;
	get_ptr_lambda_func* get_ptr_lambda;
	get_ptr_primal_solution_func* get_ptr_primal_solution;
	get_ptr_sensitivity_obj_func* get_ptr_sensitivity_obj;
	get_ptr_sensitivity_objex_func* get_ptr_sensitivity_objex;
	get_ptr_sensitivity_rhs_func* get_ptr_sensitivity_rhs;
	get_ptr_variables_func* get_ptr_variables;
	get_rh_func* get_rh;
	get_rh_range_func* get_rh_range;
	get_row_func* get_row;
	get_rowex_func* get_rowex;
	get_row_name_func* get_row_name;
	get_scalelimit_func* get_scalelimit;
	get_scaling_func* get_scaling;
	get_sensitivity_obj_func* get_sensitivity_obj;
	get_sensitivity_objex_func* get_sensitivity_objex;
	get_sensitivity_rhs_func* get_sensitivity_rhs;
	get_simplextype_func* get_simplextype;
	get_solutioncount_func* get_solutioncount;
	get_solutionlimit_func* get_solutionlimit;
	get_status_func* get_status;
	get_statustext_func* get_statustext;
	get_timeout_func* get_timeout;
	get_total_iter_func* get_total_iter;
	get_total_nodes_func* get_total_nodes;
	get_upbo_func* get_upbo;
	get_var_branch_func* get_var_branch;
	get_var_dualresult_func* get_var_dualresult;
	get_var_primalresult_func* get_var_primalresult;
	get_var_priority_func* get_var_priority;
	get_variables_func* get_variables;
	get_verbose_func* get_verbose;
	get_working_objective_func* get_working_objective;
	has_BFP_func* has_BFP;
	has_XLI_func* has_XLI;
	is_add_rowmode_func* is_add_rowmode;
	is_anti_degen_func* is_anti_degen;
	is_binary_func* is_binary;
	is_break_at_first_func* is_break_at_first;
	is_constr_type_func* is_constr_type;
	is_debug_func* is_debug;
	is_feasible_func* is_feasible;
	is_infinite_func* is_infinite;
	is_int_func* is_int;
	is_integerscaling_func* is_integerscaling;
	is_lag_trace_func* is_lag_trace;
	is_maxim_func* is_maxim;
	is_nativeBFP_func* is_nativeBFP;
	is_nativeXLI_func* is_nativeXLI;
	is_negative_func* is_negative;
	is_obj_in_basis_func* is_obj_in_basis;
	is_piv_mode_func* is_piv_mode;
	is_piv_rule_func* is_piv_rule;
	is_presolve_func* is_presolve;
	is_scalemode_func* is_scalemode;
	is_scaletype_func* is_scaletype;
	is_semicont_func* is_semicont;
	is_SOS_var_func* is_SOS_var;
	is_trace_func* is_trace;
	is_unbounded_func* is_unbounded;
	is_use_names_func* is_use_names;
	lp_solve_version_func* lp_solve_version;
	make_lp_func* make_lp;
	print_constraints_func* print_constraints;
	print_debugdump_func* print_debugdump;
	print_duals_func* print_duals;
	print_lp_func* print_lp;
	print_objective_func* print_objective;
	print_scales_func* print_scales;
	print_solution_func* print_solution;
	print_str_func* print_str;
	print_tableau_func* print_tableau;
	put_abortfunc_func* put_abortfunc;
	put_bb_nodefunc_func* put_bb_nodefunc;
	put_bb_branchfunc_func* put_bb_branchfunc;
	put_logfunc_func* put_logfunc;
	put_msgfunc_func* put_msgfunc;
	read_LP_func* read_LP;
	read_MPS_func* read_MPS;
	read_XLI_func* read_XLI;
	read_params_func* read_params;
	read_basis_func* read_basis;
	reset_basis_func* reset_basis;
	reset_params_func* reset_params;
	resize_lp_func* resize_lp;
	set_add_rowmode_func* set_add_rowmode;
	set_anti_degen_func* set_anti_degen;
	set_basisvar_func* set_basisvar;
	set_basis_func* set_basis;
	set_basiscrash_func* set_basiscrash;
	set_bb_depthlimit_func* set_bb_depthlimit;
	set_bb_floorfirst_func* set_bb_floorfirst;
	set_bb_rule_func* set_bb_rule;
	set_BFP_func* set_BFP;
	set_binary_func* set_binary;
	set_bounds_func* set_bounds;
	set_bounds_tighter_func* set_bounds_tighter;
	set_break_at_first_func* set_break_at_first;
	set_break_at_value_func* set_break_at_value;
	set_column_func* set_column;
	set_columnex_func* set_columnex;
	set_col_name_func* set_col_name;
	set_constr_type_func* set_constr_type;
	set_debug_func* set_debug;
	set_epsb_func* set_epsb;
	set_epsd_func* set_epsd;
	set_epsel_func* set_epsel;
	set_epsint_func* set_epsint;
	set_epslevel_func* set_epslevel;
	set_epsperturb_func* set_epsperturb;
	set_epspivot_func* set_epspivot;
	set_unbounded_func* set_unbounded;
	set_improve_func* set_improve;
	set_infinite_func* set_infinite;
	set_int_func* set_int;
	set_lag_trace_func* set_lag_trace;
	set_lowbo_func* set_lowbo;
	set_lp_name_func* set_lp_name;
	set_mat_func* set_mat;
	set_maxim_func* set_maxim;
	set_maxpivot_func* set_maxpivot;
	set_minim_func* set_minim;
	set_mip_gap_func* set_mip_gap;
	set_multiprice_func* set_multiprice;
	set_negrange_func* set_negrange;
	set_obj_bound_func* set_obj_bound;
	set_obj_fn_func* set_obj_fn;
	set_obj_fnex_func* set_obj_fnex;
	set_obj_func* set_obj;
	set_obj_in_basis_func* set_obj_in_basis;
	set_outputfile_func* set_outputfile;
	set_outputstream_func* set_outputstream;
	set_partialprice_func* set_partialprice;
	set_pivoting_func* set_pivoting;
	set_preferdual_func* set_preferdual;
	set_presolve_func* set_presolve;
	set_print_sol_func* set_print_sol;
	set_pseudocosts_func* set_pseudocosts;
	set_rh_func* set_rh;
	set_rh_range_func* set_rh_range;
	set_rh_vec_func* set_rh_vec;
	set_row_func* set_row;
	set_rowex_func* set_rowex;
	set_row_name_func* set_row_name;
	set_scalelimit_func* set_scalelimit;
	set_scaling_func* set_scaling;
	set_semicont_func* set_semicont;
	set_sense_func* set_sense;
	set_simplextype_func* set_simplextype;
	set_solutionlimit_func* set_solutionlimit;
	set_timeout_func* set_timeout;
	set_trace_func* set_trace;
	set_upbo_func* set_upbo;
	set_use_names_func* set_use_names;
	set_var_branch_func* set_var_branch;
	set_var_weights_func* set_var_weights;
	set_verbose_func* set_verbose;
	set_XLI_func* set_XLI;
	solve_func* solve;
	str_add_column_func* str_add_column;
	str_add_constraint_func* str_add_constraint;
	str_add_lag_con_func* str_add_lag_con;
	str_set_obj_fn_func* str_set_obj_fn;
	str_set_rh_vec_func* str_set_rh_vec;
	time_elapsed_func* time_elapsed;
	unscale_func* unscale;
	write_lp_func* write_lp;
	write_LP_func* write_LP;
	write_mps_func* write_mps;
	write_MPS_func* write_MPS;
	write_freemps_func* write_freemps;
	write_freeMPS_func* write_freeMPS;
	write_XLI_func* write_XLI;
	write_basis_func* write_basis;
	write_params_func* write_params;
	/** Spacer */
	int* alignmentspacer;
	/** Problem description */
	char* lp_name; /* The name of the model */
	/** Problem sizes */
	int sum; /* The total number of variables, including slacks */
	int rows;
	int columns;
	int equalities; /* No of non-Lagrangean equality constraints in the problem */
	int boundedvars; /* Count of bounded variables */
	int INTfuture1;
	/** Memory allocation sizes */
	int sum_alloc; /* The allocated memory for row+column-sized data */
	int rows_alloc; /* The allocated memory for row-sized data */
	int columns_alloc; /* The allocated memory for column-sized data */
	/** Model status and solver result variables */
	unsigned char source_is_file; /* The base model was read from a file */
	unsigned char model_is_pure; /* The model has been built entirely from row and column additions */
	unsigned char model_is_valid; /* Has this lp pased the 'test' */
	unsigned char tighten_on_set; /* Specify if bounds will be tightened or overriden at bound setting */
	unsigned char names_used; /* Flag to indicate if names for rows and columns are used */
	unsigned char use_row_names; /* Flag to indicate if names for rows are used */
	unsigned char use_col_names; /* Flag to indicate if names for columns are used */
	unsigned char lag_trace; /* Print information on Lagrange progression */
	unsigned char spx_trace; /* Print information on simplex progression */
	unsigned char bb_trace; /* TRUE to print extra debug information */
	unsigned char streamowned; /* TRUE if the handle should be closed at delete_lp() */
	unsigned char obj_in_basis; /* TRUE if the objective function is in the basis matrix */
	int spx_status; /* Simplex solver feasibility/mode code */
	int lag_status; /* Extra status variable for lag_solve */
	int solutioncount; /* number of equal-valued solutions found (up to solutionlimit) */
	int solutionlimit; /* upper number of equal-valued solutions kept track of */
	double real_solution; /* Optimal non-MIP solution base */
	double* solution; /* sum_alloc+1 : Solution array of the next to optimal LP,
                                   Index   0           : Objective function value,
                                   Indeces 1..rows     : Slack variable values,
                                   Indeced rows+1..sum : Variable values */
	double* best_solution; /* sum_alloc+1 : Solution array of optimal 'Integer' LP,
                                   structured as the solution array above */
	double* full_solution; /* sum_alloc+1 : Final solution array expanded for deleted variables */
	double* edgeVector; /* Array of reduced cost scaling norms (DEVEX and Steepest Edge) */
	double* drow; /* sum+1: Reduced costs of the last simplex */
	int* nzdrow; /* sum+1: Indeces of non-zero reduced costs of the last simplex */
	double* duals; /* rows_alloc+1 : The dual variables of the last LP */
	double* full_duals; /* sum_alloc+1: Final duals array expanded for deleted variables */
	double* dualsfrom; /* sum_alloc+1 :The sensitivity on dual variables/reduced costs
                                   of the last LP */
	double* dualstill; /* sum_alloc+1 :The sensitivity on dual variables/reduced costs
                                   of the last LP */
	double* objfrom; /* columns_alloc+1 :The sensitivity on objective function
                                   of the last LP */
	double* objtill; /* columns_alloc+1 :The sensitivity on objective function
                                   of the last LP */
	double* objfromvalue; /* columns_alloc+1 :The value of the variables when objective value
                                   is at its from value of the last LP */
	double* orig_obj; /* Unused pointer - Placeholder for OF not part of B */
	double* obj; /* Special vector used to temporarily change the OF vector */
	long long current_iter; /* Number of iterations in the current/last simplex */
	long long total_iter; /* Number of iterations over all B&B steps */
	long long current_bswap; /* Number of bound swaps in the current/last simplex */
	long long total_bswap; /* Number of bount swaps over all B&B steps */
	int solvecount; /* The number of solve() performed in this model */
	int max_pivots; /* Number of pivots between refactorizations of the basis */
	/** Various execution parameters */
	int simplex_strategy; /* Set desired combination of primal and dual simplex algorithms */
	int simplex_mode; /* Specifies the current simplex mode during solve; see simplex_strategy */
	int verbose; /* Set amount of run-time messages and results */
	int print_sol; /* TRUE to print optimal solution; AUTOMATIC skips zeros */
	FILE* outstream; /* Output stream, initialized to STDOUT */
	/** Main Branch and Bound settings */
	unsigned char* bb_varbranch; /* Determines branching strategy at the individual variable level;
                                   the setting here overrides the bb_floorfirst setting */
	int piv_strategy; /* Strategy for selecting row and column entering/leaving */
	int _piv_rule_; /* Internal working rule-part of piv_strategy above */
	int bb_rule; /* Rule for selecting B&B variables */
	unsigned char bb_floorfirst; /* Set BRANCH_FLOOR for B&B to set variables to floor bound first;
                                   conversely with BRANCH_CEILING, the ceiling value is set first */
	unsigned char bb_breakfirst; /* TRUE to stop at first feasible solution */
	unsigned char _piv_left_; /* Internal variable indicating active pricing loop order */
	unsigned char BOOLfuture1;
	double scalelimit; /* Relative convergence criterion for iterated scaling */
	int scalemode; /* OR-ed codes for data scaling */
	int improve; /* Set to non-zero for iterative improvement */
	int anti_degen; /* Anti-degen strategy (or none) TRUE to avoid cycling */
	int do_presolve; /* PRESOLVE_ parameters for LP presolving */
	int presolveloops; /* Maximum number of presolve loops */
	int perturb_count; /* The number of bound relaxation retries performed */
	/** Row and column names storage variables */
	hashelem** row_name; /* rows_alloc+1 */
	hashelem** col_name; /* columns_alloc+1 */
	hashtable* rowname_hashtab; /* hash table to store row names */
	hashtable* colname_hashtab; /* hash table to store column names */
	/** Optionally specify continuous rows/column blocks for partial pricing */
	partialrec* rowblocks;
	partialrec* colblocks;
	/** Row and column type codes */
	unsigned char* var_type; /* sum_alloc+1 : TRUE if variable must be integer */
	/** Data for multiple pricing */
	multirec* multivars;
	int multiblockdiv; /* The divisor used to set or augment pricing block */
	/** Variable (column) parameters */
	int fixedvars; /* The current number of basic fixed variables in the model */
	int int_vars; /* Number of variables required to be integer */
	int sc_vars; /* Number of semi-continuous variables */
	double* sc_lobound; /* sum_columns+1 : TRUE if variable is semi-continuous;
                                   value replaced by conventional lower bound during solve */
	int* var_is_free; /* columns+1: Index of twin variable if variable is free */
	int* var_priority; /* columns: Priority-mapping of variables */
	SOSgroup* GUB; /* Pointer to record containing GUBs */
	int sos_vars; /* Number of variables in the sos_priority list */
	int sos_ints; /* Number of integers in SOS'es above */
	SOSgroup* SOS; /* Pointer to record containing all SOS'es */
	int* sos_priority; /* Priority-sorted list of variables (no duplicates) */
	/** Optionally specify list of active rows/columns used in multiple pricing */
	double* bsolveVal; /* rows+1: bsolved solution vector for reduced costs */
	int* bsolveIdx; /* rows+1: Non-zero indeces of bsolveVal */
	/** RHS storage */
	double* orig_rhs; /* rows_alloc+1 : The RHS after scaling and sign
                                   changing, but before 'Bound transformation' */
	double* rhs; /* rows_alloc+1 : The RHS of the current simplex tableau */
	/** Row (constraint) parameters */
	int* row_type; /* rows_alloc+1 : Row/constraint type coding */
	/** Optionally specify data for dual long-step */
	multirec* longsteps;
	/** Original and working row and variable bounds */
	double* orig_upbo; /* sum_alloc+1 : Bound before transformations */
	double* upbo; /*  " " : Upper bound after transformation and B&B work */
	double* orig_lowbo; /*  "       "                                 */
	double* lowbo; /*  " " : Lower bound after transformation and B&B work */
	/** User data and basis factorization matrices (ETA or LU, product form) */
	MATrec* matA;
	INVrec* invB;
	/** Basis and bounds */
	BBrec* bb_bounds; /* The linked list of B&B bounds */
	BBrec* rootbounds; /* The bounds at the lowest B&B level */
	basisrec* bb_basis; /* The linked list of B&B bases */
	basisrec* rootbasis;
	OBJmonrec* monitor; /* Objective monitoring record for stalling/degeneracy handling */
	/** Scaling parameters */
	double* scalars; /* sum_alloc+1:0..Rows the scaling of the rows,
                                   Rows+1..Sum the scaling of the columns */
	unsigned char scaling_used; /* TRUE if scaling is used */
	unsigned char columns_scaled; /* TRUE if the columns are scaled too */
	unsigned char varmap_locked; /* Determines whether the var_to_orig and orig_to_var are fixed */
	/** Variable state information */
	unsigned char basis_valid; /* TRUE is the basis is still valid */
	int crashmode; /* Basis crashing mode (or none) */
	int* var_basic; /* rows_alloc+1: The list of columns in the basis */
	double* val_nonbasic; /* Array to store current values of non-basic variables */
	unsigned char* is_basic; /* sum_alloc+1: TRUE if the column is in the basis */
	unsigned char* is_lower; /*  "       " : TRUE if the variable is at its
                                   lower bound (or in the basis), FALSE otherwise */
	/** Simplex basis indicators */
	int* rejectpivot; /* List of unacceptable pivot choices due to division-by-zero */
	BBPSrec* bb_PseudoCost; /* Data structure for costing of node branchings */
	int bb_PseudoUpdates; /* Maximum number of updates for pseudo-costs */
	int bb_strongbranches; /* The number of strong B&B branches performed */
	int is_strongbranch; /* Are we currently in a strong branch mode? */
	int bb_improvements; /* The number of discrete B&B objective improvement steps */
	/** Solver working variables */
	double rhsmax; /* The maximum |value| of the rhs vector at any iteration */
	double suminfeas; /* The working sum of primal and dual infeasibilities */
	double bigM; /* Original objective weighting in primal phase 1 */
	double P1extraVal; /* Phase 1 OF/RHS offset for feasibility */
	int P1extraDim; /* Phase 1 additional columns/rows for feasibility */
	int spx_action; /* ACTION_ variables for the simplex routine */
	unsigned char spx_perturbed; /* The variable bounds were relaxed/perturbed into this simplex */
	unsigned char bb_break; /* Solver working variable; signals break of the B&B */
	unsigned char wasPreprocessed; /* The solve preprocessing was performed */
	unsigned char wasPresolved; /* The solve presolver was invoked */
	int INTfuture2;
	/** Lagragean solver storage and parameters */
	MATrec* matL;
	double* lag_rhs; /* Array of Lagrangean rhs vector */
	int* lag_con_type; /* Array of GT, LT or EQ */
	double* lambda; /* Lambda values (Lagrangean multipliers) */
	double lag_bound; /* The Lagrangian lower OF bound */
	double lag_accept; /* The Lagrangian convergence criterion */
	/** Solver thresholds */
	double infinite; /* Limit for dynamic range */
	double negrange; /* Limit for negative variable range */
	double epsmachine; /* Default machine accuracy */
	double epsvalue; /* Input data precision / rounding of data values to 0 */
	double epsprimal; /* For rounding RHS values to 0/infeasibility */
	double epsdual; /* For rounding reduced costs to zero */
	double epspivot; /* Pivot reject tolerance */
	double epsperturb; /* Perturbation scalar */
	double epssolution; /* The solution tolerance for final validation */
	/** Branch & Bound working parameters */
	int bb_status; /* Indicator that the last solvelp() gave an improved B&B solution */
	int bb_level; /* Solver B&B working variable (recursion depth) */
	int bb_maxlevel; /* The deepest B&B level of the last solution */
	int bb_limitlevel; /* The maximum B&B level allowed */
	long long bb_totalnodes; /* Total number of nodes processed in B&B */
	int bb_solutionlevel; /* The B&B level of the last / best solution */
	int bb_cutpoolsize; /* Size of the B&B cut pool */
	int bb_cutpoolused; /* Currently used cut pool */
	int bb_constraintOF; /* General purpose B&B parameter (typically for testing) */
	int* bb_cuttype; /* The type of the currently used cuts */
	int* bb_varactive; /* The B&B state of the variable; 0 means inactive */
	DeltaVrec* bb_upperchange; /* Changes to upper bounds during the B&B phase */
	DeltaVrec* bb_lowerchange; /* Changes to lower bounds during the B&B phase */
	double bb_deltaOF; /* Minimum OF step value; computed at beginning of solve() */
	double bb_breakOF; /* User-settable value for the objective function deemed
                               to be sufficiently good in an integer problem */
	double bb_limitOF; /* "Dual" bound / limit to final optimal MIP solution */
	double bb_heuristicOF; /* Set initial "at least better than" guess for objective function
                               (can significantly speed up B&B iterations) */
	double bb_parentOF; /* The OF value of the previous BB simplex */
	double bb_workOF; /* The unadjusted OF value for the current best solution */
	/** Internal work arrays allocated as required */
	presolveundorec* presolve_undo;
	workarraysrec* workarrays;
	/** MIP parameters */
	double epsint; /* Margin of error in determining if a float value is integer */
	double mip_absgap; /* Absolute MIP gap */
	double mip_relgap; /* Relative MIP gap */
	/** Time/timer variables and extended status text */
	double timecreate;
	double timestart;
	double timeheuristic;
	double timepresolved;
	double timeend;
	long sectimeout;
	/** Extended status message text set via explain() */
	char* ex_status;
	void* hBFP;
	BFPchar* bfp_name;
	BFPbool_lpintintint* bfp_compatible;
	BFPbool_lpintintchar* bfp_init;
	BFP_lp* bfp_free;
	BFPbool_lpint* bfp_resize;
	BFPint_lp* bfp_memallocated;
	BFPbool_lp* bfp_restart;
	BFPbool_lp* bfp_mustrefactorize;
	BFPint_lp* bfp_preparefactorization;
	BFPint_lpintintboolbool* bfp_factorize;
	BFP_lp* bfp_finishfactorization;
	BFP_lp* bfp_updaterefactstats;
	BFPlreal_lpintintreal* bfp_prepareupdate;
	BFPreal_lplrealreal* bfp_pivotRHS;
	BFPbool_lpbool* bfp_finishupdate;
	BFP_lprealint* bfp_ftran_prepare;
	BFP_lprealint* bfp_ftran_normal;
	BFP_lprealint* bfp_btran_normal;
	BFP_lprealintrealint* bfp_btran_double;
	BFPint_lp* bfp_status;
	BFPint_lpbool* bfp_nonzeros;
	BFPbool_lp* bfp_implicitslack;
	BFPint_lp* bfp_indexbase;
	BFPint_lp* bfp_rowoffset;
	BFPint_lp* bfp_pivotmax;
	BFPbool_lpint* bfp_pivotalloc;
	BFPint_lp* bfp_colcount;
	BFPbool_lp* bfp_canresetbasis;
	BFPreal_lp* bfp_efficiency;
	BFPrealp_lp* bfp_pivotvector;
	BFPint_lp* bfp_pivotcount;
	BFPint_lpint* bfp_refactcount;
	BFPbool_lp* bfp_isSetI;
	BFPint_lpintrealcbintint* bfp_findredundant;
	void* hXLI;
	XLIchar* xli_name;
	XLIbool_lpintintint* xli_compatible;
	XLIbool_lpcharcharcharint* xli_readmodel;
	XLIbool_lpcharcharbool* xli_writemodel;
	/** Miscellaneous internal functions made available externally */
	userabortfunc* userabort;
	reportfunc* report;
	explainfunc* explain;
	getvectorfunc* get_lpcolumn;
	getpackedfunc* get_basiscolumn;
	get_OF_activefunc* get_OF_active;
	getMDOfunc* getMDO;
	invertfunc* invert;
	set_actionfunc* set_action;
	is_actionfunc* is_action;
	clear_actionfunc* clear_action;
	/** User program interface callbacks */
	lphandle_intfunc* ctrlc;
	void* ctrlchandle; /* User-specified "owner process ID" */
	lphandlestr_func* writelog;
	void* loghandle; /* User-specified "owner process ID" */
	lphandlestr_func* debuginfo;
	lphandleint_func* usermessage;
	int msgmask;
	void* msghandle; /* User-specified "owner process ID" */
	lphandleint_intfunc* bb_usenode;
	void* bb_nodehandle; /* User-specified "owner process ID" */
	lphandleint_intfunc* bb_usebranch;
	void* bb_branchhandle; /* User-specified "owner process ID" */
	/** replacement of static variables */
	char* rowcol_name; /* The name of a row/column */
};
/** Original signature : <code>WINAPI lp_solve_version(int*, int*, int*, int*)</code> */
WINAPI lp_solve_version(int* majorversion, int* minorversion, int* release, int* build);
/** Original signature : <code>WINAPI make_lp(int, int)</code> */
WINAPI make_lp(int rows, int columns);
/** Original signature : <code>WINAPI resize_lp(lprec*, int, int)</code> */
WINAPI resize_lp(lprec* lp, int rows, int columns);
/** Original signature : <code>WINAPI get_status(lprec*)</code> */
WINAPI get_status(lprec* lp);
/** Original signature : <code>WINAPI get_statustext(lprec*, int)</code> */
WINAPI get_statustext(lprec* lp, int statuscode);
/** Original signature : <code>WINAPI is_obj_in_basis(lprec*)</code> */
WINAPI is_obj_in_basis(lprec* lp);
/** Original signature : <code>WINAPI set_obj_in_basis(lprec*, unsigned char)</code> */
WINAPI set_obj_in_basis(lprec* lp, unsigned char obj_in_basis);
/** Original signature : <code>WINAPI copy_lp(lprec*)</code> */
WINAPI copy_lp(lprec* lp);
/** Original signature : <code>WINAPI dualize_lp(lprec*)</code> */
WINAPI dualize_lp(lprec* lp);
/** Original signature : <code>char memopt_lp(lprec*, int, int, int)</code> */
unsigned char memopt_lp(lprec* lp, int rowextra, int colextra, int nzextra);
/** Original signature : <code>WINAPI delete_lp(lprec*)</code> */
WINAPI delete_lp(lprec* lp);
/** Original signature : <code>WINAPI free_lp(lprec**)</code> */
WINAPI free_lp(lprec** plp);
/** Original signature : <code>WINAPI set_lp_name(lprec*, char*)</code> */
WINAPI set_lp_name(lprec* lp, char* lpname);
/** Original signature : <code>WINAPI get_lp_name(lprec*)</code> */
WINAPI get_lp_name(lprec* lp);
/** Original signature : <code>WINAPI has_BFP(lprec*)</code> */
WINAPI has_BFP(lprec* lp);
/** Original signature : <code>WINAPI is_nativeBFP(lprec*)</code> */
WINAPI is_nativeBFP(lprec* lp);
/** Original signature : <code>WINAPI set_BFP(lprec*, char*)</code> */
WINAPI set_BFP(lprec* lp, char* filename);
/** Original signature : <code>WINAPI read_XLI(char*, char*, char*, char*, int)</code> */
WINAPI read_XLI(char* xliname, char* modelname, char* dataname, char* options, int verbose);
/** Original signature : <code>WINAPI write_XLI(lprec*, char*, char*, unsigned char)</code> */
WINAPI write_XLI(lprec* lp, char* filename, char* options, unsigned char results);
/** Original signature : <code>WINAPI has_XLI(lprec*)</code> */
WINAPI has_XLI(lprec* lp);
/** Original signature : <code>WINAPI is_nativeXLI(lprec*)</code> */
WINAPI is_nativeXLI(lprec* lp);
/** Original signature : <code>WINAPI set_XLI(lprec*, char*)</code> */
WINAPI set_XLI(lprec* lp, char* filename);
/** Original signature : <code>WINAPI set_obj(lprec*, int, double)</code> */
WINAPI set_obj(lprec* lp, int colnr, double value);
/** Original signature : <code>WINAPI set_obj_fn(lprec*, double*)</code> */
WINAPI set_obj_fn(lprec* lp, double* row);
/** Original signature : <code>WINAPI set_obj_fnex(lprec*, int, double*, int*)</code> */
WINAPI set_obj_fnex(lprec* lp, int count, double* row, int* colno);
/** Original signature : <code>WINAPI str_set_obj_fn(lprec*, char*)</code> */
WINAPI str_set_obj_fn(lprec* lp, char* row_string);
/** Original signature : <code>WINAPI set_sense(lprec*, unsigned char)</code> */
WINAPI set_sense(lprec* lp, unsigned char maximize);
/** Original signature : <code>WINAPI set_maxim(lprec*)</code> */
WINAPI set_maxim(lprec* lp);
/** Original signature : <code>WINAPI set_minim(lprec*)</code> */
WINAPI set_minim(lprec* lp);
/** Original signature : <code>WINAPI is_maxim(lprec*)</code> */
WINAPI is_maxim(lprec* lp);
/** Original signature : <code>WINAPI add_constraint(lprec*, double*, int, double)</code> */
WINAPI add_constraint(lprec* lp, double* row, int constr_type, double rh);
/** Original signature : <code>WINAPI add_constraintex(lprec*, int, double*, int*, int, double)</code> */
WINAPI add_constraintex(lprec* lp, int count, double* row, int* colno, int constr_type, double rh);
/** Original signature : <code>WINAPI set_add_rowmode(lprec*, unsigned char)</code> */
WINAPI set_add_rowmode(lprec* lp, unsigned char turnon);
/** Original signature : <code>WINAPI is_add_rowmode(lprec*)</code> */
WINAPI is_add_rowmode(lprec* lp);
/** Original signature : <code>WINAPI str_add_constraint(lprec*, char*, int, double)</code> */
WINAPI str_add_constraint(lprec* lp, char* row_string, int constr_type, double rh);
/** Original signature : <code>WINAPI set_row(lprec*, int, double*)</code> */
WINAPI set_row(lprec* lp, int rownr, double* row);
/** Original signature : <code>WINAPI set_rowex(lprec*, int, int, double*, int*)</code> */
WINAPI set_rowex(lprec* lp, int rownr, int count, double* row, int* colno);
/** Original signature : <code>WINAPI get_row(lprec*, int, double*)</code> */
WINAPI get_row(lprec* lp, int rownr, double* row);
/** Original signature : <code>WINAPI get_rowex(lprec*, int, double*, int*)</code> */
WINAPI get_rowex(lprec* lp, int rownr, double* row, int* colno);
/** Original signature : <code>WINAPI del_constraint(lprec*, int)</code> */
WINAPI del_constraint(lprec* lp, int rownr);
/** Original signature : <code>char del_constraintex(lprec*, LLrec*)</code> */
unsigned char del_constraintex(lprec* lp, LLrec* rowmap);
/** Original signature : <code>WINAPI add_lag_con(lprec*, double*, int, double)</code> */
WINAPI add_lag_con(lprec* lp, double* row, int con_type, double rhs);
/** Original signature : <code>WINAPI str_add_lag_con(lprec*, char*, int, double)</code> */
WINAPI str_add_lag_con(lprec* lp, char* row_string, int con_type, double rhs);
/** Original signature : <code>WINAPI set_lag_trace(lprec*, unsigned char)</code> */
WINAPI set_lag_trace(lprec* lp, unsigned char lag_trace);
/** Original signature : <code>WINAPI is_lag_trace(lprec*)</code> */
WINAPI is_lag_trace(lprec* lp);
/** Original signature : <code>WINAPI set_constr_type(lprec*, int, int)</code> */
WINAPI set_constr_type(lprec* lp, int rownr, int con_type);
/** Original signature : <code>WINAPI get_constr_type(lprec*, int)</code> */
WINAPI get_constr_type(lprec* lp, int rownr);
/** Original signature : <code>WINAPI get_constr_value(lprec*, int, int, double*, int*)</code> */
WINAPI get_constr_value(lprec* lp, int rownr, int count, double* primsolution, int* nzindex);
/** Original signature : <code>WINAPI is_constr_type(lprec*, int, int)</code> */
WINAPI is_constr_type(lprec* lp, int rownr, int mask);
/** Original signature : <code>char* get_str_constr_type(lprec*, int)</code> */
char* get_str_constr_type(lprec* lp, int con_type);
/** Original signature : <code>int get_constr_class(lprec*, int)</code> */
int get_constr_class(lprec* lp, int rownr);
/** Original signature : <code>char* get_str_constr_class(lprec*, int)</code> */
char* get_str_constr_class(lprec* lp, int con_class);
/** Original signature : <code>WINAPI set_rh(lprec*, int, double)</code> */
WINAPI set_rh(lprec* lp, int rownr, double value);
/** Original signature : <code>WINAPI get_rh(lprec*, int)</code> */
WINAPI get_rh(lprec* lp, int rownr);
/** Original signature : <code>WINAPI set_rh_range(lprec*, int, double)</code> */
WINAPI set_rh_range(lprec* lp, int rownr, double deltavalue);
/** Original signature : <code>WINAPI get_rh_range(lprec*, int)</code> */
WINAPI get_rh_range(lprec* lp, int rownr);
/** Original signature : <code>WINAPI set_rh_vec(lprec*, double*)</code> */
WINAPI set_rh_vec(lprec* lp, double* rh);
/** Original signature : <code>WINAPI str_set_rh_vec(lprec*, char*)</code> */
WINAPI str_set_rh_vec(lprec* lp, char* rh_string);
/** Original signature : <code>WINAPI add_column(lprec*, double*)</code> */
WINAPI add_column(lprec* lp, double* column);
/** Original signature : <code>WINAPI add_columnex(lprec*, int, double*, int*)</code> */
WINAPI add_columnex(lprec* lp, int count, double* column, int* rowno);
/** Original signature : <code>WINAPI str_add_column(lprec*, char*)</code> */
WINAPI str_add_column(lprec* lp, char* col_string);
/** Original signature : <code>WINAPI set_column(lprec*, int, double*)</code> */
WINAPI set_column(lprec* lp, int colnr, double* column);
/** Original signature : <code>WINAPI set_columnex(lprec*, int, int, double*, int*)</code> */
WINAPI set_columnex(lprec* lp, int colnr, int count, double* column, int* rowno);
/** Original signature : <code>WINAPI column_in_lp(lprec*, double*)</code> */
WINAPI column_in_lp(lprec* lp, double* column);
/** Original signature : <code>WINAPI get_columnex(lprec*, int, double*, int*)</code> */
WINAPI get_columnex(lprec* lp, int colnr, double* column, int* nzrow);
/** Original signature : <code>WINAPI get_column(lprec*, int, double*)</code> */
WINAPI get_column(lprec* lp, int colnr, double* column);
/** Original signature : <code>WINAPI del_column(lprec*, int)</code> */
WINAPI del_column(lprec* lp, int colnr);
/** Original signature : <code>char del_columnex(lprec*, LLrec*)</code> */
unsigned char del_columnex(lprec* lp, LLrec* colmap);
/** Original signature : <code>WINAPI set_mat(lprec*, int, int, double)</code> */
WINAPI set_mat(lprec* lp, int rownr, int colnr, double value);
/** Original signature : <code>WINAPI get_mat(lprec*, int, int)</code> */
WINAPI get_mat(lprec* lp, int rownr, int colnr);
/** Original signature : <code>WINAPI get_mat_byindex(lprec*, int, unsigned char, unsigned char)</code> */
WINAPI get_mat_byindex(lprec* lp, int matindex, unsigned char isrow, unsigned char adjustsign);
/** Original signature : <code>WINAPI get_nonzeros(lprec*)</code> */
WINAPI get_nonzeros(lprec* lp);
/** Original signature : <code>WINAPI set_bounds_tighter(lprec*, unsigned char)</code> */
WINAPI set_bounds_tighter(lprec* lp, unsigned char tighten);
/** Original signature : <code>char get_bounds(lprec*, int, double*, double*)</code> */
unsigned char get_bounds(lprec* lp, int column, double* lower, double* upper);
/** Original signature : <code>WINAPI get_bounds_tighter(lprec*)</code> */
WINAPI get_bounds_tighter(lprec* lp);
/** Original signature : <code>WINAPI set_upbo(lprec*, int, double)</code> */
WINAPI set_upbo(lprec* lp, int colnr, double value);
/** Original signature : <code>WINAPI get_upbo(lprec*, int)</code> */
WINAPI get_upbo(lprec* lp, int colnr);
/** Original signature : <code>WINAPI set_lowbo(lprec*, int, double)</code> */
WINAPI set_lowbo(lprec* lp, int colnr, double value);
/** Original signature : <code>WINAPI get_lowbo(lprec*, int)</code> */
WINAPI get_lowbo(lprec* lp, int colnr);
/** Original signature : <code>WINAPI set_bounds(lprec*, int, double, double)</code> */
WINAPI set_bounds(lprec* lp, int colnr, double lower, double upper);
/** Original signature : <code>WINAPI set_unbounded(lprec*, int)</code> */
WINAPI set_unbounded(lprec* lp, int colnr);
/** Original signature : <code>WINAPI is_unbounded(lprec*, int)</code> */
WINAPI is_unbounded(lprec* lp, int colnr);
/** Original signature : <code>WINAPI set_int(lprec*, int, unsigned char)</code> */
WINAPI set_int(lprec* lp, int colnr, unsigned char must_be_int);
/** Original signature : <code>WINAPI is_int(lprec*, int)</code> */
WINAPI is_int(lprec* lp, int colnr);
/** Original signature : <code>WINAPI set_binary(lprec*, int, unsigned char)</code> */
WINAPI set_binary(lprec* lp, int colnr, unsigned char must_be_bin);
/** Original signature : <code>WINAPI is_binary(lprec*, int)</code> */
WINAPI is_binary(lprec* lp, int colnr);
/** Original signature : <code>WINAPI set_semicont(lprec*, int, unsigned char)</code> */
WINAPI set_semicont(lprec* lp, int colnr, unsigned char must_be_sc);
/** Original signature : <code>WINAPI is_semicont(lprec*, int)</code> */
WINAPI is_semicont(lprec* lp, int colnr);
/** Original signature : <code>WINAPI is_negative(lprec*, int)</code> */
WINAPI is_negative(lprec* lp, int colnr);
/** Original signature : <code>WINAPI set_var_weights(lprec*, double*)</code> */
WINAPI set_var_weights(lprec* lp, double* weights);
/** Original signature : <code>WINAPI get_var_priority(lprec*, int)</code> */
WINAPI get_var_priority(lprec* lp, int colnr);
/** Original signature : <code>WINAPI set_pseudocosts(lprec*, double*, double*, int*)</code> */
WINAPI set_pseudocosts(lprec* lp, double* clower, double* cupper, int* updatelimit);
/** Original signature : <code>WINAPI get_pseudocosts(lprec*, double*, double*, int*)</code> */
WINAPI get_pseudocosts(lprec* lp, double* clower, double* cupper, int* updatelimit);
/** Original signature : <code>WINAPI add_SOS(lprec*, char*, int, int, int, int*, double*)</code> */
WINAPI add_SOS(lprec* lp, char* name, int sostype, int priority, int count, int* sosvars, double* weights);
/** Original signature : <code>WINAPI is_SOS_var(lprec*, int)</code> */
WINAPI is_SOS_var(lprec* lp, int colnr);
/** Original signature : <code>WINAPI set_row_name(lprec*, int, char*)</code> */
WINAPI set_row_name(lprec* lp, int rownr, char* new_name);
/** Original signature : <code>WINAPI get_row_name(lprec*, int)</code> */
WINAPI get_row_name(lprec* lp, int rownr);
/** Original signature : <code>WINAPI get_origrow_name(lprec*, int)</code> */
WINAPI get_origrow_name(lprec* lp, int rownr);
/** Original signature : <code>WINAPI set_col_name(lprec*, int, char*)</code> */
WINAPI set_col_name(lprec* lp, int colnr, char* new_name);
/** Original signature : <code>WINAPI get_col_name(lprec*, int)</code> */
WINAPI get_col_name(lprec* lp, int colnr);
/** Original signature : <code>WINAPI get_origcol_name(lprec*, int)</code> */
WINAPI get_origcol_name(lprec* lp, int colnr);
/** Original signature : <code>WINAPI unscale(lprec*)</code> */
WINAPI unscale(lprec* lp);
/** Original signature : <code>WINAPI set_preferdual(lprec*, unsigned char)</code> */
WINAPI set_preferdual(lprec* lp, unsigned char dodual);
/** Original signature : <code>WINAPI set_simplextype(lprec*, int)</code> */
WINAPI set_simplextype(lprec* lp, int simplextype);
/** Original signature : <code>WINAPI get_simplextype(lprec*)</code> */
WINAPI get_simplextype(lprec* lp);
/** Original signature : <code>WINAPI default_basis(lprec*)</code> */
WINAPI default_basis(lprec* lp);
/** Original signature : <code>WINAPI set_basiscrash(lprec*, int)</code> */
WINAPI set_basiscrash(lprec* lp, int mode);
/** Original signature : <code>WINAPI get_basiscrash(lprec*)</code> */
WINAPI get_basiscrash(lprec* lp);
/** Original signature : <code>WINAPI set_basisvar(lprec*, int, int)</code> */
WINAPI set_basisvar(lprec* lp, int basisPos, int enteringCol);
/** Original signature : <code>WINAPI set_basis(lprec*, int*, unsigned char)</code> */
WINAPI set_basis(lprec* lp, int* bascolumn, unsigned char nonbasic);
/** Original signature : <code>WINAPI get_basis(lprec*, int*, unsigned char)</code> */
WINAPI get_basis(lprec* lp, int* bascolumn, unsigned char nonbasic);
/** Original signature : <code>WINAPI reset_basis(lprec*)</code> */
WINAPI reset_basis(lprec* lp);
/** Original signature : <code>WINAPI guess_basis(lprec*, double*, int*)</code> */
WINAPI guess_basis(lprec* lp, double* guessvector, int* basisvector);
/** Original signature : <code>WINAPI is_feasible(lprec*, double*, double)</code> */
WINAPI is_feasible(lprec* lp, double* values, double threshold);
/** Original signature : <code>WINAPI solve(lprec*)</code> */
WINAPI solve(lprec* lp);
/** Original signature : <code>WINAPI time_elapsed(lprec*)</code> */
WINAPI time_elapsed(lprec* lp);
/** Original signature : <code>WINAPI put_bb_nodefunc(lprec*, lphandleint_intfunc, void*)</code> */
WINAPI put_bb_nodefunc(lprec* lp, lphandleint_intfunc newnode, void* bbnodehandle);
/** Original signature : <code>WINAPI put_bb_branchfunc(lprec*, lphandleint_intfunc, void*)</code> */
WINAPI put_bb_branchfunc(lprec* lp, lphandleint_intfunc newbranch, void* bbbranchhandle);
/** Original signature : <code>WINAPI put_abortfunc(lprec*, lphandle_intfunc, void*)</code> */
WINAPI put_abortfunc(lprec* lp, lphandle_intfunc newctrlc, void* ctrlchandle);
/** Original signature : <code>WINAPI put_logfunc(lprec*, lphandlestr_func, void*)</code> */
WINAPI put_logfunc(lprec* lp, lphandlestr_func newlog, void* loghandle);
/** Original signature : <code>WINAPI put_msgfunc(lprec*, lphandleint_func, void*, int)</code> */
WINAPI put_msgfunc(lprec* lp, lphandleint_func newmsg, void* msghandle, int mask);
/** Original signature : <code>WINAPI get_primal_solution(lprec*, double*)</code> */
WINAPI get_primal_solution(lprec* lp, double* pv);
/** Original signature : <code>WINAPI get_ptr_primal_solution(lprec*, double**)</code> */
WINAPI get_ptr_primal_solution(lprec* lp, double** pv);
/** Original signature : <code>WINAPI get_dual_solution(lprec*, double*)</code> */
WINAPI get_dual_solution(lprec* lp, double* rc);
/** Original signature : <code>WINAPI get_ptr_dual_solution(lprec*, double**)</code> */
WINAPI get_ptr_dual_solution(lprec* lp, double** rc);
/** Original signature : <code>WINAPI get_lambda(lprec*, double*)</code> */
WINAPI get_lambda(lprec* lp, double* lambda);
/** Original signature : <code>WINAPI get_ptr_lambda(lprec*, double**)</code> */
WINAPI get_ptr_lambda(lprec* lp, double** lambda);
/** Original signature : <code>WINAPI read_MPS(char*, int)</code> */
WINAPI read_MPS(char* filename, int options);
/** Original signature : <code>WINAPI read_mps(FILE*, int)</code> */
WINAPI read_mps(FILE* filename, int options);
/** Original signature : <code>WINAPI read_freeMPS(char*, int)</code> */
WINAPI read_freeMPS(char* filename, int options);
/** Original signature : <code>WINAPI read_freemps(FILE*, int)</code> */
WINAPI read_freemps(FILE* filename, int options);
/** Original signature : <code>WINAPI write_mps(lprec*, char*)</code> */
WINAPI write_mps(lprec* lp, char* filename);
/** Original signature : <code>WINAPI write_MPS(lprec*, FILE*)</code> */
WINAPI write_MPS(lprec* lp, FILE* output);
/** Original signature : <code>WINAPI write_freemps(lprec*, char*)</code> */
WINAPI write_freemps(lprec* lp, char* filename);
/** Original signature : <code>WINAPI write_freeMPS(lprec*, FILE*)</code> */
WINAPI write_freeMPS(lprec* lp, FILE* output);
/** Original signature : <code>WINAPI write_lp(lprec*, char*)</code> */
WINAPI write_lp(lprec* lp, char* filename);
/** Original signature : <code>WINAPI write_LP(lprec*, FILE*)</code> */
WINAPI write_LP(lprec* lp, FILE* output);
/** Original signature : <code>WINAPI LP_readhandle(lprec**, FILE*, int, char*)</code> */
WINAPI LP_readhandle(lprec** lp, FILE* filename, int verbose, char* lp_name);
/** Original signature : <code>WINAPI read_lp(FILE*, int, char*)</code> */
WINAPI read_lp(FILE* filename, int verbose, char* lp_name);
/** Original signature : <code>WINAPI read_LP(char*, int, char*)</code> */
WINAPI read_LP(char* filename, int verbose, char* lp_name);
/** Original signature : <code>WINAPI write_basis(lprec*, char*)</code> */
WINAPI write_basis(lprec* lp, char* filename);
/** Original signature : <code>WINAPI read_basis(lprec*, char*, char*)</code> */
WINAPI read_basis(lprec* lp, char* filename, char* info);
/** Original signature : <code>WINAPI write_params(lprec*, char*, char*)</code> */
WINAPI write_params(lprec* lp, char* filename, char* options);
/** Original signature : <code>WINAPI read_params(lprec*, char*, char*)</code> */
WINAPI read_params(lprec* lp, char* filename, char* options);
/** Original signature : <code>WINAPI reset_params(lprec*)</code> */
WINAPI reset_params(lprec* lp);
/** Original signature : <code>WINAPI print_lp(lprec*)</code> */
WINAPI print_lp(lprec* lp);
/** Original signature : <code>WINAPI print_tableau(lprec*)</code> */
WINAPI print_tableau(lprec* lp);
/** Original signature : <code>WINAPI print_objective(lprec*)</code> */
WINAPI print_objective(lprec* lp);
/** Original signature : <code>WINAPI print_solution(lprec*, int)</code> */
WINAPI print_solution(lprec* lp, int columns);
/** Original signature : <code>WINAPI print_constraints(lprec*, int)</code> */
WINAPI print_constraints(lprec* lp, int columns);
/** Original signature : <code>WINAPI print_duals(lprec*)</code> */
WINAPI print_duals(lprec* lp);
/** Original signature : <code>WINAPI print_scales(lprec*)</code> */
WINAPI print_scales(lprec* lp);
/** Original signature : <code>WINAPI print_str(lprec*, char*)</code> */
WINAPI print_str(lprec* lp, char* str);
/** Original signature : <code>WINAPI set_outputstream(lprec*, FILE*)</code> */
WINAPI set_outputstream(lprec* lp, FILE* stream);
/** Original signature : <code>WINAPI set_outputfile(lprec*, char*)</code> */
WINAPI set_outputfile(lprec* lp, char* filename);
/** Original signature : <code>WINAPI set_verbose(lprec*, int)</code> */
WINAPI set_verbose(lprec* lp, int verbose);
/** Original signature : <code>WINAPI get_verbose(lprec*)</code> */
WINAPI get_verbose(lprec* lp);
/** Original signature : <code>WINAPI set_timeout(lprec*, long)</code> */
WINAPI set_timeout(lprec* lp, long sectimeout);
/** Original signature : <code>long WINAPI get_timeout(lprec*)</code> */
long WINAPI get_timeout(lprec* lp);
/** Original signature : <code>WINAPI set_print_sol(lprec*, int)</code> */
WINAPI set_print_sol(lprec* lp, int print_sol);
/** Original signature : <code>WINAPI get_print_sol(lprec*)</code> */
WINAPI get_print_sol(lprec* lp);
/** Original signature : <code>WINAPI set_debug(lprec*, unsigned char)</code> */
WINAPI set_debug(lprec* lp, unsigned char debug);
/** Original signature : <code>WINAPI is_debug(lprec*)</code> */
WINAPI is_debug(lprec* lp);
/** Original signature : <code>WINAPI set_trace(lprec*, unsigned char)</code> */
WINAPI set_trace(lprec* lp, unsigned char trace);
/** Original signature : <code>WINAPI is_trace(lprec*)</code> */
WINAPI is_trace(lprec* lp);
/** Original signature : <code>WINAPI print_debugdump(lprec*, char*)</code> */
WINAPI print_debugdump(lprec* lp, char* filename);
/** Original signature : <code>WINAPI set_anti_degen(lprec*, int)</code> */
WINAPI set_anti_degen(lprec* lp, int anti_degen);
/** Original signature : <code>WINAPI get_anti_degen(lprec*)</code> */
WINAPI get_anti_degen(lprec* lp);
/** Original signature : <code>WINAPI is_anti_degen(lprec*, int)</code> */
WINAPI is_anti_degen(lprec* lp, int testmask);
/** Original signature : <code>WINAPI set_presolve(lprec*, int, int)</code> */
WINAPI set_presolve(lprec* lp, int presolvemode, int maxloops);
/** Original signature : <code>WINAPI get_presolve(lprec*)</code> */
WINAPI get_presolve(lprec* lp);
/** Original signature : <code>WINAPI get_presolveloops(lprec*)</code> */
WINAPI get_presolveloops(lprec* lp);
/** Original signature : <code>WINAPI is_presolve(lprec*, int)</code> */
WINAPI is_presolve(lprec* lp, int testmask);
/** Original signature : <code>WINAPI get_orig_index(lprec*, int)</code> */
WINAPI get_orig_index(lprec* lp, int lp_index);
/** Original signature : <code>WINAPI get_lp_index(lprec*, int)</code> */
WINAPI get_lp_index(lprec* lp, int orig_index);
/** Original signature : <code>WINAPI set_maxpivot(lprec*, int)</code> */
WINAPI set_maxpivot(lprec* lp, int max_num_inv);
/** Original signature : <code>WINAPI get_maxpivot(lprec*)</code> */
WINAPI get_maxpivot(lprec* lp);
/** Original signature : <code>WINAPI set_obj_bound(lprec*, double)</code> */
WINAPI set_obj_bound(lprec* lp, double obj_bound);
/** Original signature : <code>WINAPI get_obj_bound(lprec*)</code> */
WINAPI get_obj_bound(lprec* lp);
/** Original signature : <code>WINAPI set_mip_gap(lprec*, unsigned char, double)</code> */
WINAPI set_mip_gap(lprec* lp, unsigned char absolute, double mip_gap);
/** Original signature : <code>WINAPI get_mip_gap(lprec*, unsigned char)</code> */
WINAPI get_mip_gap(lprec* lp, unsigned char absolute);
/** Original signature : <code>WINAPI set_bb_rule(lprec*, int)</code> */
WINAPI set_bb_rule(lprec* lp, int bb_rule);
/** Original signature : <code>WINAPI get_bb_rule(lprec*)</code> */
WINAPI get_bb_rule(lprec* lp);
/** Original signature : <code>WINAPI set_var_branch(lprec*, int, int)</code> */
WINAPI set_var_branch(lprec* lp, int colnr, int branch_mode);
/** Original signature : <code>WINAPI get_var_branch(lprec*, int)</code> */
WINAPI get_var_branch(lprec* lp, int colnr);
/** Original signature : <code>WINAPI is_infinite(lprec*, double)</code> */
WINAPI is_infinite(lprec* lp, double value);
/** Original signature : <code>WINAPI set_infinite(lprec*, double)</code> */
WINAPI set_infinite(lprec* lp, double infinite);
/** Original signature : <code>WINAPI get_infinite(lprec*)</code> */
WINAPI get_infinite(lprec* lp);
/** Original signature : <code>WINAPI set_epsint(lprec*, double)</code> */
WINAPI set_epsint(lprec* lp, double epsint);
/** Original signature : <code>WINAPI get_epsint(lprec*)</code> */
WINAPI get_epsint(lprec* lp);
/** Original signature : <code>WINAPI set_epsb(lprec*, double)</code> */
WINAPI set_epsb(lprec* lp, double epsb);
/** Original signature : <code>WINAPI get_epsb(lprec*)</code> */
WINAPI get_epsb(lprec* lp);
/** Original signature : <code>WINAPI set_epsd(lprec*, double)</code> */
WINAPI set_epsd(lprec* lp, double epsd);
/** Original signature : <code>WINAPI get_epsd(lprec*)</code> */
WINAPI get_epsd(lprec* lp);
/** Original signature : <code>WINAPI set_epsel(lprec*, double)</code> */
WINAPI set_epsel(lprec* lp, double epsel);
/** Original signature : <code>WINAPI get_epsel(lprec*)</code> */
WINAPI get_epsel(lprec* lp);
/** Original signature : <code>WINAPI set_epslevel(lprec*, int)</code> */
WINAPI set_epslevel(lprec* lp, int epslevel);
/** Original signature : <code>WINAPI set_scaling(lprec*, int)</code> */
WINAPI set_scaling(lprec* lp, int scalemode);
/** Original signature : <code>WINAPI get_scaling(lprec*)</code> */
WINAPI get_scaling(lprec* lp);
/** Original signature : <code>WINAPI is_scalemode(lprec*, int)</code> */
WINAPI is_scalemode(lprec* lp, int testmask);
/** Original signature : <code>WINAPI is_scaletype(lprec*, int)</code> */
WINAPI is_scaletype(lprec* lp, int scaletype);
/** Original signature : <code>WINAPI is_integerscaling(lprec*)</code> */
WINAPI is_integerscaling(lprec* lp);
/** Original signature : <code>WINAPI set_scalelimit(lprec*, double)</code> */
WINAPI set_scalelimit(lprec* lp, double scalelimit);
/** Original signature : <code>WINAPI get_scalelimit(lprec*)</code> */
WINAPI get_scalelimit(lprec* lp);
/** Original signature : <code>WINAPI set_improve(lprec*, int)</code> */
WINAPI set_improve(lprec* lp, int improve);
/** Original signature : <code>WINAPI get_improve(lprec*)</code> */
WINAPI get_improve(lprec* lp);
/** Original signature : <code>WINAPI set_pivoting(lprec*, int)</code> */
WINAPI set_pivoting(lprec* lp, int piv_rule);
/** Original signature : <code>WINAPI get_pivoting(lprec*)</code> */
WINAPI get_pivoting(lprec* lp);
/** Original signature : <code>WINAPI set_partialprice(lprec*, int, int*, unsigned char)</code> */
WINAPI set_partialprice(lprec* lp, int blockcount, int* blockstart, unsigned char isrow);
/** Original signature : <code>WINAPI get_partialprice(lprec*, int*, int*, unsigned char)</code> */
WINAPI get_partialprice(lprec* lp, int* blockcount, int* blockstart, unsigned char isrow);
/** Original signature : <code>WINAPI set_multiprice(lprec*, int)</code> */
WINAPI set_multiprice(lprec* lp, int multiblockdiv);
/** Original signature : <code>WINAPI get_multiprice(lprec*, unsigned char)</code> */
WINAPI get_multiprice(lprec* lp, unsigned char getabssize);
/** Original signature : <code>WINAPI is_use_names(lprec*, unsigned char)</code> */
WINAPI is_use_names(lprec* lp, unsigned char isrow);
/** Original signature : <code>WINAPI set_use_names(lprec*, unsigned char, unsigned char)</code> */
WINAPI set_use_names(lprec* lp, unsigned char isrow, unsigned char use_names);
/** Original signature : <code>WINAPI get_nameindex(lprec*, char*, unsigned char)</code> */
WINAPI get_nameindex(lprec* lp, char* varname, unsigned char isrow);
/** Original signature : <code>WINAPI is_piv_mode(lprec*, int)</code> */
WINAPI is_piv_mode(lprec* lp, int testmask);
/** Original signature : <code>WINAPI is_piv_rule(lprec*, int)</code> */
WINAPI is_piv_rule(lprec* lp, int rule);
/** Original signature : <code>WINAPI set_break_at_first(lprec*, unsigned char)</code> */
WINAPI set_break_at_first(lprec* lp, unsigned char break_at_first);
/** Original signature : <code>WINAPI is_break_at_first(lprec*)</code> */
WINAPI is_break_at_first(lprec* lp);
/** Original signature : <code>WINAPI set_bb_floorfirst(lprec*, int)</code> */
WINAPI set_bb_floorfirst(lprec* lp, int bb_floorfirst);
/** Original signature : <code>WINAPI get_bb_floorfirst(lprec*)</code> */
WINAPI get_bb_floorfirst(lprec* lp);
/** Original signature : <code>WINAPI set_bb_depthlimit(lprec*, int)</code> */
WINAPI set_bb_depthlimit(lprec* lp, int bb_maxlevel);
/** Original signature : <code>WINAPI get_bb_depthlimit(lprec*)</code> */
WINAPI get_bb_depthlimit(lprec* lp);
/** Original signature : <code>WINAPI set_break_at_value(lprec*, double)</code> */
WINAPI set_break_at_value(lprec* lp, double break_at_value);
/** Original signature : <code>WINAPI get_break_at_value(lprec*)</code> */
WINAPI get_break_at_value(lprec* lp);
/** Original signature : <code>WINAPI set_negrange(lprec*, double)</code> */
WINAPI set_negrange(lprec* lp, double negrange);
/** Original signature : <code>WINAPI get_negrange(lprec*)</code> */
WINAPI get_negrange(lprec* lp);
/** Original signature : <code>WINAPI set_epsperturb(lprec*, double)</code> */
WINAPI set_epsperturb(lprec* lp, double epsperturb);
/** Original signature : <code>WINAPI get_epsperturb(lprec*)</code> */
WINAPI get_epsperturb(lprec* lp);
/** Original signature : <code>WINAPI set_epspivot(lprec*, double)</code> */
WINAPI set_epspivot(lprec* lp, double epspivot);
/** Original signature : <code>WINAPI get_epspivot(lprec*)</code> */
WINAPI get_epspivot(lprec* lp);
/** Original signature : <code>WINAPI get_max_level(lprec*)</code> */
WINAPI get_max_level(lprec* lp);
/** Original signature : <code>long long WINAPI get_total_nodes(lprec*)</code> */
long long WINAPI get_total_nodes(lprec* lp);
/** Original signature : <code>long long WINAPI get_total_iter(lprec*)</code> */
long long WINAPI get_total_iter(lprec* lp);
/** Original signature : <code>WINAPI get_objective(lprec*)</code> */
WINAPI get_objective(lprec* lp);
/** Original signature : <code>WINAPI get_working_objective(lprec*)</code> */
WINAPI get_working_objective(lprec* lp);
/** Original signature : <code>WINAPI get_var_primalresult(lprec*, int)</code> */
WINAPI get_var_primalresult(lprec* lp, int index);
/** Original signature : <code>WINAPI get_var_dualresult(lprec*, int)</code> */
WINAPI get_var_dualresult(lprec* lp, int index);
/** Original signature : <code>WINAPI get_variables(lprec*, double*)</code> */
WINAPI get_variables(lprec* lp, double* var);
/** Original signature : <code>WINAPI get_ptr_variables(lprec*, double**)</code> */
WINAPI get_ptr_variables(lprec* lp, double** var);
/** Original signature : <code>WINAPI get_constraints(lprec*, double*)</code> */
WINAPI get_constraints(lprec* lp, double* constr);
/** Original signature : <code>WINAPI get_ptr_constraints(lprec*, double**)</code> */
WINAPI get_ptr_constraints(lprec* lp, double** constr);
/** Original signature : <code>WINAPI get_sensitivity_rhs(lprec*, double*, double*, double*)</code> */
WINAPI get_sensitivity_rhs(lprec* lp, double* duals, double* dualsfrom, double* dualstill);
/** Original signature : <code>WINAPI get_ptr_sensitivity_rhs(lprec*, double**, double**, double**)</code> */
WINAPI get_ptr_sensitivity_rhs(lprec* lp, double** duals, double** dualsfrom, double** dualstill);
/** Original signature : <code>WINAPI get_sensitivity_obj(lprec*, double*, double*)</code> */
WINAPI get_sensitivity_obj(lprec* lp, double* objfrom, double* objtill);
/** Original signature : <code>WINAPI get_sensitivity_objex(lprec*, double*, double*, double*, double*)</code> */
WINAPI get_sensitivity_objex(lprec* lp, double* objfrom, double* objtill, double* objfromvalue, double* objtillvalue);
/** Original signature : <code>WINAPI get_ptr_sensitivity_obj(lprec*, double**, double**)</code> */
WINAPI get_ptr_sensitivity_obj(lprec* lp, double** objfrom, double** objtill);
/** Original signature : <code>WINAPI get_ptr_sensitivity_objex(lprec*, double**, double**, double**, double**)</code> */
WINAPI get_ptr_sensitivity_objex(lprec* lp, double** objfrom, double** objtill, double** objfromvalue, double** objtillvalue);
/** Original signature : <code>WINAPI set_solutionlimit(lprec*, int)</code> */
WINAPI set_solutionlimit(lprec* lp, int limit);
/** Original signature : <code>WINAPI get_solutionlimit(lprec*)</code> */
WINAPI get_solutionlimit(lprec* lp);
/** Original signature : <code>WINAPI get_solutioncount(lprec*)</code> */
WINAPI get_solutioncount(lprec* lp);
/** Original signature : <code>WINAPI get_Norig_rows(lprec*)</code> */
WINAPI get_Norig_rows(lprec* lp);
/** Original signature : <code>WINAPI get_Nrows(lprec*)</code> */
WINAPI get_Nrows(lprec* lp);
/** Original signature : <code>WINAPI get_Lrows(lprec*)</code> */
WINAPI get_Lrows(lprec* lp);
/** Original signature : <code>WINAPI get_Norig_columns(lprec*)</code> */
WINAPI get_Norig_columns(lprec* lp);
/** Original signature : <code>WINAPI get_Ncolumns(lprec*)</code> */
WINAPI get_Ncolumns(lprec* lp);
typedef int WINAPI(void* userhandle, char* buf, int max_size);
typedef int WINAPI(void* userhandle, char* buf);
/** Original signature : <code>WINAPI MPS_readex(lprec**, void*, read_modeldata_func, int, int)</code> */
WINAPI MPS_readex(lprec** newlp, void* userhandle, read_modeldata_func read_modeldata, int typeMPS, int options);
/** Original signature : <code>WINAPI read_lpex(void*, read_modeldata_func, int, char*)</code> */
WINAPI read_lpex(void* userhandle, read_modeldata_func read_modeldata, int verbose, char* lp_name);
/** Original signature : <code>WINAPI write_lpex(lprec*, void*, write_modeldata_func)</code> */
WINAPI write_lpex(lprec* lp, void* userhandle, write_modeldata_func write_modeldata);
/** Original signature : <code>WINAPI read_mpsex(void*, read_modeldata_func, int)</code> */
WINAPI read_mpsex(void* userhandle, read_modeldata_func read_modeldata, int options);
/** Original signature : <code>WINAPI read_freempsex(void*, read_modeldata_func, int)</code> */
WINAPI read_freempsex(void* userhandle, read_modeldata_func read_modeldata, int options);
/** Original signature : <code>WINAPI MPS_writefileex(lprec*, int, void*, write_modeldata_func)</code> */
WINAPI MPS_writefileex(lprec* lp, int typeMPS, void* userhandle, write_modeldata_func write_modeldata);
/**
 * Forward definitions of functions used internaly by the lp toolkit<br>
 * Original signature : <code>char set_callbacks(lprec*)</code>
 */
unsigned char set_callbacks(lprec* lp);
/** Original signature : <code>int yieldformessages(lprec*)</code> */
int yieldformessages(lprec* lp);
/** Original signature : <code>WINAPI userabort(lprec*, int)</code> */
WINAPI userabort(lprec* lp, int message);
/**
 * Memory management routines<br>
 * Original signature : <code>char append_rows(lprec*, int)</code>
 */
unsigned char append_rows(lprec* lp, int deltarows);
/** Original signature : <code>char append_columns(lprec*, int)</code> */
unsigned char append_columns(lprec* lp, int deltacolumns);
/** Original signature : <code>void inc_rows(lprec*, int)</code> */
void inc_rows(lprec* lp, int delta);
/** Original signature : <code>void inc_columns(lprec*, int)</code> */
void inc_columns(lprec* lp, int delta);
/** Original signature : <code>char init_rowcol_names(lprec*)</code> */
unsigned char init_rowcol_names(lprec* lp);
/** Original signature : <code>char inc_row_space(lprec*, int)</code> */
unsigned char inc_row_space(lprec* lp, int deltarows);
/** Original signature : <code>char inc_col_space(lprec*, int)</code> */
unsigned char inc_col_space(lprec* lp, int deltacols);
/** Original signature : <code>char shift_rowcoldata(lprec*, int, int, LLrec*, unsigned char)</code> */
unsigned char shift_rowcoldata(lprec* lp, int base, int delta, LLrec* usedmap, unsigned char isrow);
/** Original signature : <code>char shift_basis(lprec*, int, int, LLrec*, unsigned char)</code> */
unsigned char shift_basis(lprec* lp, int base, int delta, LLrec* usedmap, unsigned char isrow);
/** Original signature : <code>char shift_rowdata(lprec*, int, int, LLrec*)</code> */
unsigned char shift_rowdata(lprec* lp, int base, int delta, LLrec* usedmap);
/** Original signature : <code>char shift_coldata(lprec*, int, int, LLrec*)</code> */
unsigned char shift_coldata(lprec* lp, int base, int delta, LLrec* usedmap);
/**
 * INLINE<br>
 * Original signature : <code>char is_chsign(lprec*, int)</code>
 */
unsigned char is_chsign(lprec* lp, int rownr);
/** Original signature : <code>char inc_lag_space(lprec*, int, unsigned char)</code> */
unsigned char inc_lag_space(lprec* lp, int deltarows, unsigned char ignoreMAT);
/** Original signature : <code>lprec* make_lag(lprec*)</code> */
lprec* make_lag(lprec* server);
/** Original signature : <code>double get_rh_upper(lprec*, int)</code> */
double get_rh_upper(lprec* lp, int rownr);
/** Original signature : <code>double get_rh_lower(lprec*, int)</code> */
double get_rh_lower(lprec* lp, int rownr);
/** Original signature : <code>char set_rh_upper(lprec*, int, double)</code> */
unsigned char set_rh_upper(lprec* lp, int rownr, double value);
/** Original signature : <code>char set_rh_lower(lprec*, int, double)</code> */
unsigned char set_rh_lower(lprec* lp, int rownr, double value);
/** Original signature : <code>int bin_count(lprec*, unsigned char)</code> */
int bin_count(lprec* lp, unsigned char working);
/** Original signature : <code>int MIP_count(lprec*)</code> */
int MIP_count(lprec* lp);
/** Original signature : <code>int SOS_count(lprec*)</code> */
int SOS_count(lprec* lp);
/** Original signature : <code>int GUB_count(lprec*)</code> */
int GUB_count(lprec* lp);
/** Original signature : <code>int identify_GUB(lprec*, unsigned char)</code> */
int identify_GUB(lprec* lp, unsigned char mark);
/** Original signature : <code>int prepare_GUB(lprec*)</code> */
int prepare_GUB(lprec* lp);
/** Original signature : <code>char refactRecent(lprec*)</code> */
unsigned char refactRecent(lprec* lp);
/** Original signature : <code>char check_if_less(lprec*, double, double, int)</code> */
unsigned char check_if_less(lprec* lp, double x, double y, int variable);
/** Original signature : <code>char feasiblePhase1(lprec*, double)</code> */
unsigned char feasiblePhase1(lprec* lp, double epsvalue);
/** Original signature : <code>void free_duals(lprec*)</code> */
void free_duals(lprec* lp);
/** Original signature : <code>void initialize_solution(lprec*, unsigned char)</code> */
void initialize_solution(lprec* lp, unsigned char shiftbounds);
/** Original signature : <code>void recompute_solution(lprec*, unsigned char)</code> */
void recompute_solution(lprec* lp, unsigned char shiftbounds);
/** Original signature : <code>int verify_solution(lprec*, unsigned char, char*)</code> */
int verify_solution(lprec* lp, unsigned char reinvert, char* info);
/** Original signature : <code>int check_solution(lprec*, int, double*, double*, double*, double)</code> */
int check_solution(lprec* lp, int lastcolumn, double* solution, double* upbo, double* lowbo, double tolerance);
/**
 * INLINE<br>
 * Original signature : <code>char is_fixedvar(lprec*, int)</code>
 */
unsigned char is_fixedvar(lprec* lp, int variable);
/**
 * INLINE<br>
 * Original signature : <code>char is_splitvar(lprec*, int)</code>
 */
unsigned char is_splitvar(lprec* lp, int colnr);
/** Original signature : <code>WINAPI set_action(int*, int)</code> */
WINAPI set_action(int* actionvar, int actionmask);
/** Original signature : <code>WINAPI clear_action(int*, int)</code> */
WINAPI clear_action(int* actionvar, int actionmask);
/** Original signature : <code>WINAPI is_action(int, int)</code> */
WINAPI is_action(int actionvar, int testmask);
/**
 * INLINE<br>
 * Original signature : <code>char is_bb_rule(lprec*, int)</code>
 */
unsigned char is_bb_rule(lprec* lp, int bb_rule);
/**
 * INLINE<br>
 * Original signature : <code>char is_bb_mode(lprec*, int)</code>
 */
unsigned char is_bb_mode(lprec* lp, int bb_mask);
/**
 * INLINE<br>
 * Original signature : <code>int get_piv_rule(lprec*)</code>
 */
int get_piv_rule(lprec* lp);
/** Original signature : <code>char* get_str_piv_rule(int)</code> */
char* get_str_piv_rule(int rule);
/** Original signature : <code>WINAPI set_var_priority(lprec*)</code> */
WINAPI set_var_priority(lprec* lp);
/** Original signature : <code>int find_sc_bbvar(lprec*, int*)</code> */
int find_sc_bbvar(lprec* lp, int* count);
/** Original signature : <code>int find_sos_bbvar(lprec*, int*, unsigned char)</code> */
int find_sos_bbvar(lprec* lp, int* count, unsigned char intsos);
/** Original signature : <code>int find_int_bbvar(lprec*, int*, BBrec*, unsigned char*)</code> */
int find_int_bbvar(lprec* lp, int* count, BBrec* BB, unsigned char* isfeasible);
/**
 * Solution-related functions<br>
 * Original signature : <code>double compute_dualslacks(lprec*, int, double**, int**, unsigned char)</code>
 */
double compute_dualslacks(lprec* lp, int target, double** dvalues, int** nzdvalues, unsigned char dosum);
/** Original signature : <code>char solution_is_int(lprec*, int, unsigned char)</code> */
unsigned char solution_is_int(lprec* lp, int index, unsigned char checkfixed);
/** Original signature : <code>char bb_better(lprec*, int, int)</code> */
unsigned char bb_better(lprec* lp, int target, int mode);
/** Original signature : <code>void construct_solution(lprec*, double*)</code> */
void construct_solution(lprec* lp, double* target);
/** Original signature : <code>void transfer_solution_var(lprec*, int)</code> */
void transfer_solution_var(lprec* lp, int uservar);
/** Original signature : <code>char construct_duals(lprec*)</code> */
unsigned char construct_duals(lprec* lp);
/** Original signature : <code>char construct_sensitivity_duals(lprec*)</code> */
unsigned char construct_sensitivity_duals(lprec* lp);
/** Original signature : <code>char construct_sensitivity_obj(lprec*)</code> */
unsigned char construct_sensitivity_obj(lprec* lp);
/** Original signature : <code>int add_GUB(lprec*, char*, int, int, int*)</code> */
int add_GUB(lprec* lp, char* name, int priority, int count, int* sosvars);
/** Original signature : <code>basisrec* push_basis(lprec*, int*, unsigned char*, unsigned char*)</code> */
basisrec* push_basis(lprec* lp, int* basisvar, unsigned char* isbasic, unsigned char* islower);
/** Original signature : <code>char compare_basis(lprec*)</code> */
unsigned char compare_basis(lprec* lp);
/** Original signature : <code>char restore_basis(lprec*)</code> */
unsigned char restore_basis(lprec* lp);
/** Original signature : <code>char pop_basis(lprec*, unsigned char)</code> */
unsigned char pop_basis(lprec* lp, unsigned char restore);
/** Original signature : <code>char is_BasisReady(lprec*)</code> */
unsigned char is_BasisReady(lprec* lp);
/** Original signature : <code>char is_slackbasis(lprec*)</code> */
unsigned char is_slackbasis(lprec* lp);
/** Original signature : <code>char verify_basis(lprec*)</code> */
unsigned char verify_basis(lprec* lp);
/** Original signature : <code>int unload_basis(lprec*, unsigned char)</code> */
int unload_basis(lprec* lp, unsigned char restorelast);
/** Original signature : <code>int perturb_bounds(lprec*, BBrec*, unsigned char, unsigned char, unsigned char)</code> */
int perturb_bounds(lprec* lp, BBrec* perturbed, unsigned char doRows, unsigned char doCols, unsigned char includeFIXED);
/** Original signature : <code>char validate_bounds(lprec*, double*, double*)</code> */
unsigned char validate_bounds(lprec* lp, double* upbo, double* lowbo);
/** Original signature : <code>char impose_bounds(lprec*, double*, double*)</code> */
unsigned char impose_bounds(lprec* lp, double* upbo, double* lowbo);
/** Original signature : <code>int unload_BB(lprec*)</code> */
int unload_BB(lprec* lp);
/** Original signature : <code>double feasibilityOffset(lprec*, unsigned char)</code> */
double feasibilityOffset(lprec* lp, unsigned char isdual);
/** Original signature : <code>char isP1extra(lprec*)</code> */
unsigned char isP1extra(lprec* lp);
/** Original signature : <code>double get_refactfrequency(lprec*, unsigned final char)</code> */
double get_refactfrequency(lprec* lp, unsigned final char char1);
/** Original signature : <code>int findBasicFixedvar(lprec*, int, unsigned char)</code> */
int findBasicFixedvar(lprec* lp, int afternr, unsigned char slacksonly);
/** Original signature : <code>char isBasisVarFeasible(lprec*, double, int)</code> */
unsigned char isBasisVarFeasible(lprec* lp, double tol, int basis_row);
/** Original signature : <code>char isPrimalFeasible(lprec*, double, int[], double*)</code> */
unsigned char isPrimalFeasible(lprec* lp, double tol, int infeasibles[], double* feasibilitygap);
/** Original signature : <code>char isDualFeasible(lprec*, double, int*, int[], double*)</code> */
unsigned char isDualFeasible(lprec* lp, double tol, int* boundflips, int infeasibles[], double* feasibilitygap);
/**
 * Main simplex driver routines<br>
 * Original signature : <code>int preprocess(lprec*)</code>
 */
int preprocess(lprec* lp);
/** Original signature : <code>void postprocess(lprec*)</code> */
void postprocess(lprec* lp);
/** Original signature : <code>char performiteration(lprec*, int, int, double, unsigned char, unsigned char, double*, int*, double*, int*, int*)</code> */
unsigned char performiteration(lprec* lp, int rownr, int varin, double theta, unsigned char primal, unsigned char allowminit, double* prow, int* nzprow, double* pcol, int* nzpcol, int* boundswaps);
/** Original signature : <code>void transfer_solution_var(lprec*, int)</code> */
void transfer_solution_var(lprec* lp, int uservar);
/** Original signature : <code>void transfer_solution(lprec*, unsigned char)</code> */
void transfer_solution(lprec* lp, unsigned char dofinal);
/**
 * Scaling utilities<br>
 * Original signature : <code>double scaled_floor(lprec*, int, double, double)</code>
 */
double scaled_floor(lprec* lp, int colnr, double value, double epsscale);
/** Original signature : <code>double scaled_ceil(lprec*, int, double, double)</code> */
double scaled_ceil(lprec* lp, int colnr, double value, double epsscale);
/**
 * Variable mapping utility routines<br>
 * Original signature : <code>void varmap_lock(lprec*)</code>
 */
void varmap_lock(lprec* lp);
/** Original signature : <code>void varmap_clear(lprec*)</code> */
void varmap_clear(lprec* lp);
/** Original signature : <code>char varmap_canunlock(lprec*)</code> */
unsigned char varmap_canunlock(lprec* lp);
/** Original signature : <code>void varmap_addconstraint(lprec*)</code> */
void varmap_addconstraint(lprec* lp);
/** Original signature : <code>void varmap_addcolumn(lprec*)</code> */
void varmap_addcolumn(lprec* lp);
/** Original signature : <code>void varmap_delete(lprec*, int, int, LLrec*)</code> */
void varmap_delete(lprec* lp, int base, int delta, LLrec* varmap);
/** Original signature : <code>void varmap_compact(lprec*, int, int)</code> */
void varmap_compact(lprec* lp, int prev_rows, int prev_cols);
/** Original signature : <code>char varmap_validate(lprec*, int)</code> */
unsigned char varmap_validate(lprec* lp, int varno);
/**
 * STATIC MYBOOL del_varnameex(lprec *lp, hashelem **namelist, hashtable *ht, int varnr, LLrec *varmap);<br>
 * Original signature : <code>char del_varnameex(lprec*, hashelem**, int, hashtable*, int, LLrec*)</code>
 */
unsigned char del_varnameex(lprec* lp, hashelem** namelist, int items, hashtable* ht, int varnr, LLrec* varmap);
/**
 * Pseudo-cost routines (internal)<br>
 * Original signature : <code>BBPSrec* init_pseudocost(lprec*, int)</code>
 */
BBPSrec* init_pseudocost(lprec* lp, int pseudotype);
/** Original signature : <code>void free_pseudocost(lprec*)</code> */
void free_pseudocost(lprec* lp);
/** Original signature : <code>double get_pseudorange(BBPSrec*, int, int)</code> */
double get_pseudorange(BBPSrec* pc, int mipvar, int varcode);
/** Original signature : <code>void update_pseudocost(BBPSrec*, int, int, unsigned char, double)</code> */
void update_pseudocost(BBPSrec* pc, int mipvar, int varcode, unsigned char capupper, double varsol);
/** Original signature : <code>double get_pseudobranchcost(BBPSrec*, int, unsigned char)</code> */
double get_pseudobranchcost(BBPSrec* pc, int mipvar, unsigned char dofloor);
/** Original signature : <code>double get_pseudonodecost(BBPSrec*, int, int, double)</code> */
double get_pseudonodecost(BBPSrec* pc, int mipvar, int vartype, double varsol);
/**
 * Matrix access and equation solving routines<br>
 * Original signature : <code>void set_OF_override(lprec*, double*)</code>
 */
void set_OF_override(lprec* lp, double* ofVector);
/** Original signature : <code>void set_OF_p1extra(lprec*, double)</code> */
void set_OF_p1extra(lprec* lp, double p1extra);
/** Original signature : <code>void unset_OF_p1extra(lprec*)</code> */
void unset_OF_p1extra(lprec* lp);
/** Original signature : <code>char modifyOF1(lprec*, int, double*, double)</code> */
unsigned char modifyOF1(lprec* lp, int index, double* ofValue, double mult);
/** Original signature : <code>WINAPI get_OF_active(lprec*, int, double)</code> */
WINAPI get_OF_active(lprec* lp, int varnr, double mult);
/** Original signature : <code>char is_OF_nz(lprec*, int)</code> */
unsigned char is_OF_nz(lprec* lp, int colnr);
/** Original signature : <code>int get_basisOF(lprec*, int[], double[], int[])</code> */
int get_basisOF(lprec* lp, int coltarget[], double crow[], int colno[]);
/** Original signature : <code>WINAPI get_basiscolumn(lprec*, int, int[], double[])</code> */
WINAPI get_basiscolumn(lprec* lp, int j, int rn[], double bj[]);
/** Original signature : <code>WINAPI obtain_column(lprec*, int, double*, int*, int*)</code> */
WINAPI obtain_column(lprec* lp, int varin, double* pcol, int* nzlist, int* maxabs);
/** Original signature : <code>int compute_theta(lprec*, int, double*, int, double, unsigned char)</code> */
int compute_theta(lprec* lp, int rownr, double* theta, int isupbound, double HarrisScalar, unsigned char primal);
/**
 * Pivot utility routines<br>
 * Original signature : <code>int findBasisPos(lprec*, int, int*)</code>
 */
int findBasisPos(lprec* lp, int notint, int* var_basic);
/** Original signature : <code>char check_degeneracy(lprec*, double*, int*)</code> */
unsigned char check_degeneracy(lprec* lp, double* pcol, int* degencount);
